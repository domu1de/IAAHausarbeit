/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.authentication;

import de.nak.exammgmt.persistence.dao.user.UserSessionDAO;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.persistence.entity.user.UserSession;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.service.user.GuestProvider;
import de.nak.exammgmt.util.DigestUtils;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.stream;

/**
 * Default implementation of the AuthenticationService that uses cookies to save the sessions.
 *
 * NOTE: This service must be request scoped to work properly!
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultAuthenticationService implements AuthenticationService {

    private UserSessionDAO userSessionDAO;
    private GuestProvider guestProvider;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private User currentUser;

    @Override
    public User authenticate() {
        UserSession userSession;
        UserSessionCookie userSessionCookie = UserSessionCookie.from(request.getCookies());

        if (userSessionCookie == null) {
            return guestProvider.getCurrentInstance();
        }

        userSession = userSessionDAO.findByTokenAndUser(hash(userSessionCookie.getToken()), userSessionCookie.getUserId());

        if (userSession == null) {
            return guestProvider.getCurrentInstance();
        }

        renewUserSession(userSession);
        userSession.getUser().setCurrentUserSession(userSession);

        currentUser = userSession.getUser();

        return currentUser;
    }

    @Override
    public void createUserSession(User user, boolean rememberMe) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        UserSession userSession = new UserSession();
        userSession.setUser(user);
        userSession.setRememberMe(rememberMe);
        renewUserSession(userSession);
        userSessionDAO.save(userSession);
    }

    @Override
    public List<UserSession> listUserSessions(User user) throws NotFoundException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        return userSessionDAO.findByUser(user);
    }

    @Override
    public void revokeUserSession(long id, User user) throws NotFoundException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        UserSession userSession = userSessionDAO.findById(id);

        if (userSession == null) {
            throw new NotFoundException(UserSession.class);
        }

        if (!userSession.getUser().equals(user)) {
            throw new RuntimeException("You cannot revoke another user's sessions.");
        }

        userSessionDAO.delete(userSession);
    }

    @Override
    public void revokeUserSessions(User user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        userSessionDAO.deleteByUser(user);
    }

    @Override
    public void revokeCurrentUserSession(User user) throws NotFoundException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        UserSession currentUserSession = user.getCurrentUserSession();

        if (currentUserSession == null) {
            throw new NotFoundException(UserSession.class);
        }

        userSessionDAO.delete(currentUserSession);
        response.addCookie(UserSessionCookie.deleteCookie());
    }

    @Override
    public User getCurrentUser() {
        if (currentUser == null) {
            return authenticate();
        }
        return currentUser;
    }

    /**
     * Renews and thereby extends the given UserSession.
     *
     * @param userSession the UserSession to renew.
     */
    private void renewUserSession(UserSession userSession) {
        String token = generateToken();

        userSession.setIp(request.getRemoteAddr());
        userSession.setUserAgent(request.getHeader("user-agent"));
        userSession.setToken(hash(token));

        Cookie userSessionCookie = new UserSessionCookie(userSession.getUser().getId(), token, userSession.isRememberMe());
        userSessionCookie.setPath(request.getContextPath());
        response.addCookie(userSessionCookie);
    }

    /**
     * Generates a 32 byte long token.
     *
     * @return the generated token.
     */
    private String generateToken() {
        return new String(Hex.encode(KeyGenerators.secureRandom(32).generateKey()));
    }

    /**
     * Hashes the given data.
     *
     * @param data the data to hash.
     * @return the hashed data.
     */
    private String hash(String data) {
        return DigestUtils.shaHex(data);
    }

    public void setUserSessionDAO(UserSessionDAO userSessionDAO) {
        this.userSessionDAO = userSessionDAO;
    }

    public void setGuestProvider(GuestProvider guestProvider) {
        this.guestProvider = guestProvider;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * Custom Cookie to store a UserSession.
     */
    static class UserSessionCookie extends Cookie {

        private static final String COOKIE_NAME = "user_session";
        private static final String COOKIE_SEPARATOR = ":";
        private static final Duration REMEMBER_ME_DURATION = Duration.ofDays(14);

        private Long userId;
        private String token;

        private UserSessionCookie(Long userId, String token) {
            super(COOKIE_NAME, token + COOKIE_SEPARATOR + userId);

            this.userId = userId;
            this.token = token;

            setHttpOnly(true);
        }

        public UserSessionCookie(Long userId, String token, boolean rememberMe) {
            this(userId, token);

            if (rememberMe) {
                setMaxAge((int) REMEMBER_ME_DURATION.getSeconds());
            }
        }

        /**
         * Factory to create a {@code UserSessionCookie} out of {@code Cookies[]}.
         *
         * @param cookies the cookie array
         * @return the created UserSessionCookie
         */
        public static UserSessionCookie from(Cookie[] cookies) {
            Cookie cookie = getCookie(cookies);
            if (cookie == null) {
                return null;
            }
            String[] tokenAndValue = cookie.getValue().split(COOKIE_SEPARATOR);
            if (tokenAndValue.length != 2) {
                return null;
            }
            return new UserSessionCookie(Long.parseLong(tokenAndValue[1]), tokenAndValue[0]);
        }

        /**
         * Provides a Cookie which forces the browser to delete the UserSessionCookie
         *
         * @return Cookie that will delete the UserSessionCookie
         */
        public static Cookie deleteCookie() {
            Cookie cookieToDelete = new Cookie(COOKIE_NAME, "");
            cookieToDelete.setHttpOnly(true);
            cookieToDelete.setMaxAge(0);
            return cookieToDelete;
        }

        /**
         * Filters the UserSessionCookie out of the given cookies-array.
         *
         * @param cookies Array of cookies from which the UserSessionCookie will be filtered.
         * @return the correct cookie or null if not in the array.
         */
        private static Cookie getCookie(Cookie[] cookies) {
            if (cookies == null) {
                return null;
            }
            return stream(cookies)
                    .filter(c -> c.getName().equals(COOKIE_NAME))
                    .findFirst()
                    .orElse(null);
        }

        public Long getUserId() {
            return userId;
        }

        public String getToken() {
            return token;
        }
    }

}
