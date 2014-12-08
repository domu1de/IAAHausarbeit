/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.home;

import de.nak.exammgmt.persistence.entity.user.Role;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.AlreadyActivatedException;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service that manages operations for admin users.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface AdminService {

    /**
     * Creates the given user.
     *
     * @param user the user to create, not null.
     * @throws AlreadyCreatedException if the user is already created.
     * @throws AlreadyActivatedException if the user has already been activated.
     */
    void createUser(User user) throws AlreadyCreatedException, AlreadyActivatedException;

    /**
     * Updates the given user.
     *
     * @param user the user to update, not null.
     */
    void updateUser(User user);

    /**
     * Deactivates the user with the given id. Users cannot be deleted due to integrity reasons.
     * Cancels all processes that might be active and could illegally reactivate the user.
     *
     * @param userId id of the user to deactivate.
     * @throws NotFoundException if the user could not be found.
     */
    void deactivateUser(Long userId) throws NotFoundException;

    /**
     * Reactives the deactived user with the given id and therefore reinits the activation process.
     * See {@see AdminService#deactivateUser} for more information.
     *
     * @param userId id of the user to reactive.
     * @throws NotFoundException if the user could not be found.
     * @throws AlreadyActivatedException if the user has already been activated.
     */
    void reactiveUser(Long userId) throws NotFoundException, AlreadyActivatedException;

    /**
     * Gets the user for the given id.
     *
     * @param userId the id of the user.
     * @return the persisted user with the id.
     * @throws NotFoundException if the user could not be found.
     */
    User getUser(Long userId) throws NotFoundException;

    /**
     * Lists all users in the system.
     *
     * @return List of all users.
     */
    List<User> listUsers();

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

    /**
     * Lists all Roles in the system except the guest role.
     *
     * @return List of all Roles.
     */
    List<Role> getRoles();

}
