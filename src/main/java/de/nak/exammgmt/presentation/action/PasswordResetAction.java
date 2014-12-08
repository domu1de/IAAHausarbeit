/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.service.exception.InvalidTokenException;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.service.user.PasswordResetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RESTful action to manage PasswordReset resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class PasswordResetAction extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetAction.class);

    private PasswordResetService passwordResetService;

    private String token;
    private String usernameOrEmail;
    private String password;
    private String confirmPassword;

    public String index() {
        return INDEX;
    }

    public String show() {
        if (!passwordResetService.validateResetRequest(token)) {
            addActionError(getText("txt.passwordReset.invalid"));
            return INDEX;
        }
        return INPUT;
    }

    public String create() {
        if (usernameOrEmail == null) {
            addActionError("No username or email.");
            return ERROR;
        }
        try {
            passwordResetService.initResetProcess(usernameOrEmail);
            LOGGER.info("Password reset send to " + usernameOrEmail + ".");
        } catch (NotFoundException e) {
            addActionError(getText("txt.userNotFound"));
            return ERROR;
        }
        return REDIRECT_WELCOME;
    }

    public String update() {
        if (password != null && !password.equals(confirmPassword)) {
            addActionError(getText("txt.passwordsDoNotMatch"));
            return INPUT;
        }

        try {
            passwordResetService.finishPasswordReset(token, password);
        } catch (InvalidTokenException e) {
            addActionError(getText("txt.passwordReset.invalid"));
            return INDEX;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        addActionMessage(getText("txt.passwordReset.successful"));
        return LOGIN;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}
