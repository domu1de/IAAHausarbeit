/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.user;

import de.nak.nakexammgmt.persistence.dao.DAO;
import de.nak.nakexammgmt.persistence.entity.user.User;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link User} entities.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface UserDAO extends DAO<User> {

    /**
     * Finds the user by one of its unique fields username or email.
     *
     * @param usernameOrEmail   the username or email to search for.
     * @return  the user.
     */
    User findByUsernameOrEmail(String usernameOrEmail);

    /**
     * Finds a list of all inative users.
     *
     * @return List of all inactive users.
     */
    List<User> findInactiveUsers();

    /**
     * Finds a list of all active users.
     *
     * @return  List of all active users.
     */
    List<User> findActiveUsers();

}
