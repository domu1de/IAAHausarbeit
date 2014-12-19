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
     * Finds the role entity with the given name.
     *
     * @param name name of the role
     * @return role with the given name, or {@code null}
     */
    Role findByName(String name);

    /**
     * Finds a list of all Roles except the guest role.
     *
     * @return list of all roles without guest
     */
    List<Role> findAllWithoutGuest();

}
