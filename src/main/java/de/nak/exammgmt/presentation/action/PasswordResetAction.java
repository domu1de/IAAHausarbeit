/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.service.exception.InvalidTokenException;
import de.nak.exammgmt.service.user.PasswordResetService;

/**
 * RESTful action to manage PasswordReset resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class PasswordResetAction extends BaseAction {

    private PasswordResetService passwordResetService;

    private String token;
    private String usernameOrEmail;
    private String password;
    private String confirmPassword;

    @Override
    public String index() {
        return INDEX;
    }

    @Override
    public String show() {
        if (!passwordResetService.validateResetRequest(token)) {
            addActionError(getText("txt.passwordReset.invalid"));
            return INDEX;
        }
        return INPUT;
    }

    @Override
    public String create() throws Exception {
        if (usernameOrEmail == null) {
            return ERROR;
        }

        passwordResetService.initResetProcess(usernameOrEmail);
        return REDIRECT_WELCOME;
    }

    @Override
    public String update() throws Exception {
        if (password != null && !password.equals(confirmPassword)) {
            addActionError(getText("txt.passwordsDoNotMatch"));
            return INPUT;
        }

        try {
            passwordResetService.finishPasswordReset(token, password);
        } catch (InvalidTokenException e) {
            addActionError(getText("txt.passwordReset.invalid"));
            return INDEX;
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
