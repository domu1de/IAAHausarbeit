/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * Contract for a service to manage everything regarding {@link Employee}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface EmployeeService {

    /**
     * Gets the employee with the given id.
     *
     * @param employeeId the id of the student
     * @return the employee
     * @throws NotFoundException if no entity could be found
     */
    Employee get(long employeeId) throws NotFoundException;

    /**
     * Get the employee for the given user.
     *
     * @param user the associated user
     * @return the employee
     * @throws NotFoundException if no entity could be found
     */
    Employee getByUser(User user) throws NotFoundException;

}
