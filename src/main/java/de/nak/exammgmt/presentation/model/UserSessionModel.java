/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.user.UserSession;

import java.util.List;

/**
 * Model to encapsulate UserSession entities against the presentation layer using the concept of form beans.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class UserSessionModel {

    private List<UserSession> userSessions;

    public List<UserSession> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(List<UserSession> userSessions) {
        this.userSessions = userSessions;
    }
}
