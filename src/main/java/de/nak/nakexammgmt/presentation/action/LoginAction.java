/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import de.nak.nakexammgmt.service.common.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RESTful action to manage logins as resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class LoginAction extends BaseAction {

    private static final String RETURN_TO = "return_to";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);

    private LoginService loginService;

    private String username;
    private String password;
    private boolean rememberMe;

    public String show() {
        if (!getCurrentUser().isLoggedIn()) {
            return INPUT;
        }
        return REDIRECT_WELCOME;
    }

    public String login() {
        if (loginService.validateLogin(username, password, rememberMe)) {
            if (returnTo != null && !returnTo.isEmpty()) {
                return RETURN_TO;
            }
            return REDIRECT_WELCOME;
        }
        return INPUT;
    }

    /**
     * Logs out the current user and redirects him to welcome.
     *
     * @return Redirect to welcome or error if failed.
     */
    public String logout() {
        try {
            loginService.logout(getCurrentUser());
            LOGGER.info(getCurrentUser().getUsername() + " logged out.");
            return REDIRECT_WELCOME;
        } catch (Exception e) {  // TODO specify
            addActionError("Logout failed.");
            e.printStackTrace();
            return ERROR;
        }
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
