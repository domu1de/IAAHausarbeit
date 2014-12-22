/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.authentication;

import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.persistence.entity.user.UserSession;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service managing the authentication of the System.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface AuthenticationService {

    /**
     * Authenticates the current session, updates it and returns the current user.
     *
     * @return the current user (could be guest)
     */
    User authenticate();

    /**
     * Creates a new UserSession for the given user. The rememberMe-feature creates a longer session.
     *
     * @param user the User for whom the session will be created, not null
     * @param rememberMe flag to indicate if the session should be longer
     */
    void createUserSession(User user, boolean rememberMe);

    /**
     * Lists all UserSessions of the given user in descending order by their last usage.
     *
     * @param user the user whose sessions to list, not null
     * @return List of all UserSessions of the user
     * @throws NotFoundException if the user is not in the system.
     */
    List<UserSession> listUserSessions(User user) throws NotFoundException;

    /**
     * Revokes the given UserSession with the given user. Users can only revoke their own UserSessions.
     *
     * @param id   identifier of the UserSession to remove
     * @param user the user who removes the Session, not null
     * @throws NotFoundException if the user is not in the system
     * @throws Exception´if the user tries to revoke someone else's session
     */
    void revokeUserSession(long id, User user) throws NotFoundException, Exception; //TODO specify exception

    /**
     * Revokes all UserSessions of the given user.
     *
     * @param user the user whose UserSessions will be revoked, not null
     */
    void revokeUserSessions(User user);

    /**
     * Revokes the current UserSession of the given User.
     *
     * @param user the user whose current UserSession will be revoked, not null
     * @throws NotFoundException if there is no current UserSession for the user
     */
    void revokeCurrentUserSession(User user) throws NotFoundException;

    /**
     * Gets the currently authenticated user.
     *
     * @return the current user (could be guest)
     */
    User getCurrentUser();

}
