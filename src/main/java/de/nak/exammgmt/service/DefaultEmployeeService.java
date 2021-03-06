/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.EmployeeDAO;
import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.Objects;

/**
 * Default implementation of {@link EmployeeService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultEmployeeService implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Override
    public Employee get(long employeeId) throws NotFoundException {
        Employee employee = employeeDAO.findById(employeeId);
        if (employee == null) {
            throw new NotFoundException(Employee.class);
        }
        return employee;
    }

    @Override
    public Employee getByUser(User user) throws NotFoundException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        Employee employee = employeeDAO.findByUser(user);
        if (employee == null) {
            throw new NotFoundException(Employee.class);
        }
        return employee;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
