/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.user;

import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage Users.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface UserService {

    /**
     * Creates the given user.
     *
     * @param user the user to create, not null.
     * @throws de.nak.exammgmt.service.exception.AlreadyCreatedException if the user has already been created.
     */
    void create(User user) throws AlreadyCreatedException;

    /**
     * Updates the given user.
     *
     * @param user the user to update, not null.
     */
    void update(User user);

    /**
     * Updates the password for the given user. The new password will be hashed.
     *
     * @param user     the user whose password will be updated, not null.
     * @param password the new, unhashed password, not null.
     * @throws de.nak.exammgmt.service.exception.NotFoundException
     */
    void updatePassword(User user, String password) throws NotFoundException;

    /**
     * Activates the given user.
     *
     * @param user the user to deactivate, not null.
     */
    void activate(User user);

    /**
     * Deactivates the given user and deletes his password. Users cannot be deleted due to integrity reasons.
     *
     * @param user the user to deactivate, not null.
     */
    void deactivate(User user);

    /**
     * Gets the user with the given id.
     *
     * @param id the id of the user.
     * @return the user.
     * @throws de.nak.exammgmt.service.exception.NotFoundException
     */
    User get(Long id) throws NotFoundException;

    /**
     * Gets the user for the given credentials.
     *
     * @param usernameOrEmail the username or email of the user to get.
     * @return the user.
     * @throws de.nak.exammgmt.service.exception.NotFoundException
     */
    User get(String usernameOrEmail) throws NotFoundException;

    /**
     * Lists all users in the system.
     *
     * @return List of all users.
     */
    List<User> list();

    /**
     * Lists all inactive users in the system.
     *
     * @return List of all inactive users.
     */
    List<User> listInactiveUsers();

    /**
     * Lists all active users in the system.
     *
     * @return List of all active users.
     */
    List<User> listActiveUsers();

}
