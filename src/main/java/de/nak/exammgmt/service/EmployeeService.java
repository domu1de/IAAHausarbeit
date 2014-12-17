/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface EmployeeService {

    Employee get(long employeeId) throws NotFoundException;

    Employee get(User user) throws NotFoundException;

}
