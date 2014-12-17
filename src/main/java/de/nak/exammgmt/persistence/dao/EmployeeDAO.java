/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao;

import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.user.User;

/**
 * Data Access Object to provide persisted {@link Employee} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface EmployeeDAO extends DAO<Employee> {

    /**
     * Finds the corresponding employee for the given user
     *
     * @param user the corresponding user
     * @return the employee, or null
     */
    Employee findByUser(User user);

}
