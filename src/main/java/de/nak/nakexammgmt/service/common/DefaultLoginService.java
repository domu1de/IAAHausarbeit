/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.common;

import de.nak.nakexammgmt.persistence.dao.user.UserDAO;
import de.nak.nakexammgmt.persistence.entity.user.User;
import de.nak.nakexammgmt.service.authentication.AuthenticationService;
import de.nak.nakexammgmt.service.exception.NotFoundException;
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
    // TODO: boolean -> exception
    public boolean validateLogin(String usernameOrEmail, String password, boolean rememberMe) {
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
