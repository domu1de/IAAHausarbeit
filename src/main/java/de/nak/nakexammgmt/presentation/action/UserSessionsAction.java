/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import de.nak.nakexammgmt.presentation.model.UserSessionModel;
import de.nak.nakexammgmt.service.authentication.AuthenticationService;
import de.nak.nakexammgmt.service.exception.NotFoundException;

/**
 * RESTful action to manage UserSession resources.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class UserSessionsAction extends BaseAction {

    private AuthenticationService authenticationService;

    private UserSessionModel userSessionModel = new UserSessionModel();
    private Long userSessionId;

    public String index() {
        try {
            userSessionModel.setUserSessions(authenticationService.listUserSessions(getCurrentUser()));
        } catch (NotFoundException e) {
            // TODO: loggen? Dieser fehler dürfte niemals auftreten
            return ERROR;
        }
        return INDEX;
    }

    public String remove() {
        if (userSessionId == null) {
            return NOT_FOUND;
        }

        if (getCurrentUser().getCurrentUserSession().getId().equals(userSessionId)) {
            return NOT_FOUND; // TODO: oder anderes result?
        }

        try {
            authenticationService.revokeUserSession(userSessionId, getCurrentUser());
            return INDEX;
        } catch (Exception e) {
            // TODO: refactor.
        }

        return ERROR;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public UserSessionModel getUserSessionModel() {
        return userSessionModel;
    }

    public void setUserSessionModel(UserSessionModel userSessionModel) {
        this.userSessionModel = userSessionModel;
    }

    public Long getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(Long userSessionId) {
        this.userSessionId = userSessionId;
    }
}
