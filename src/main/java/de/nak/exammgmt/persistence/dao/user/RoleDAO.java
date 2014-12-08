/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.user;

import de.nak.exammgmt.persistence.dao.DAO;
import de.nak.exammgmt.persistence.entity.user.Role;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link Role} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface RoleDAO extends DAO<Role> {

    /**
     * Finds the Role entity with the given name.
     *
     * @param name name of the role
     * @return Role with the given name or null if not present.
     */
    Role findByName(String name);

    /**
     * Finds a list of all Roles except the guest role.
     *
     * @return List of all roles without guest.
     */
    List<Role> findAllWithoutGuest();
}
