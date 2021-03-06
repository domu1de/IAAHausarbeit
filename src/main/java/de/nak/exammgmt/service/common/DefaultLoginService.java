/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;

import de.nak.exammgmt.persistence.dao.user.UserDAO;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.authentication.AuthenticationService;
import de.nak.exammgmt.service.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Default implementation of the LoginService.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultLoginService implements LoginService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;
    private SessionService sessionService;

    @Override
    public boolean login(String usernameOrEmail, String password, boolean rememberMe) {
        User user = userDAO.findByUsernameOrEmail(usernameOrEmail);

        if (user == null) {
            return false;
        }

        if (!user.isActivated()) {
            return false;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return false;
        }

        sessionService.invalidateSession();
        authenticationService.createUserSession(user, rememberMe);
        return true;
    }

    @Override
    public void logout(User user) throws NotFoundException {
        authenticationService.revokeCurrentUserSession(user);
        sessionService.invalidateSession();
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

}
