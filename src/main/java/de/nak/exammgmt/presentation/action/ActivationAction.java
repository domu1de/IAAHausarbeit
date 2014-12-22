/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.service.exception.InvalidTokenException;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.service.user.ActivationService;

/**
 * RESTful action to manage Activation resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ActivationAction extends BaseAction {

    private ActivationService activationService;

    private String token;
    private String password;
    private String confirmPassword;

    public String show() {
        if (!activationService.validate(token)) {
            addActionError(getText("txt.activation.invalid"));
            return ERROR;
        }
        return INPUT;
    }


    public String update() {
        if (password != null && !password.equals(confirmPassword)) {
            addActionError(getText("txt.passwordsDoNotMatch"));
            return INPUT;
        }

        try {
            activationService.finish(token, password);
        } catch (InvalidTokenException e) {
            addActionError(getText("txt.activation.invalid"));
            return ERROR;
        } catch (NotFoundException e) {
            return NOT_FOUND;
        }

        addActionMessage(getText("txt.activation.successful"));
        return LOGIN;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setActivationService(ActivationService activationService) {
        this.activationService = activationService;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
