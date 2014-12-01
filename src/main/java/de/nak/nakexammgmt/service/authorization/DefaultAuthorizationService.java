/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.authorization;

import de.nak.nakexammgmt.persistence.entity.user.Permission;
import de.nak.nakexammgmt.persistence.entity.user.User;

import java.util.Set;

/**
 * Default implementation of the AuthorizationService.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultAuthorizationService implements AuthorizationService {

    @Override
    public AuthorizationStatus accessGranted(Set<Permission> permissions, User user) {
        if (!user.hasRights(permissions)) {
            if (!user.isLoggedIn()) {
                return AuthorizationStatus.LOGIN_NEEDED;
            }
            return AuthorizationStatus.ACCESS_DENIED;
        }
        return AuthorizationStatus.ACCESS_GRANTED;
    }

}
