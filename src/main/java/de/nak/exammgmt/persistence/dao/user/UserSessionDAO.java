/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.user;

import de.nak.exammgmt.persistence.dao.DAO;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.persistence.entity.user.UserSession;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link UserSession} entities.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface UserSessionDAO extends DAO<UserSession> {

    /**
     * Finds the UserSession for the given user and token.
     *
     * @param token the token to look for.
     * @param userId    the id of the user to look for.
     * @return  the UserSession.
     */
    UserSession findByTokenAndUser(String token, Long userId);

    /**
     * Finds a list of all UserSessions for the given user.
     *
     * @param user  the user whose UserSessions will be searched.
     * @return  List of all UserSessions of the user.
     */
    List<UserSession> findByUser(User user);

    void deleteByUser(User user);

}
