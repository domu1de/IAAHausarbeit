/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.authorization;

import de.nak.nakexammgmt.persistence.entity.user.Permission;
import de.nak.nakexammgmt.persistence.entity.user.User;

import java.util.Set;

/**
 * Contract for a service to manage the authorization in the system. Provides three states of authorization.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface AuthorizationService {

    /**
     * Determines the AuthorizationStatus for the given User and the needed permissions.
     *
     * @param permissions Set of Permissions the user needs to be granted access.
     * @param user        the user whose AuthorizationStatus shall be determined.
     * @return the AuthorizationStatus of the user, not null.
     */
    AuthorizationStatus accessGranted(Set<Permission> permissions, User user);

    /**
     * Possible authorisation states of the system.
     */
    public enum AuthorizationStatus {
        LOGIN_NEEDED,
        ACCESS_GRANTED,
        ACCESS_DENIED
    }
}
