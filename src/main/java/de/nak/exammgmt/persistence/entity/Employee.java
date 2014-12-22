/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity to store an Employee. We intentionally do not differentiate between lecturers and other staff.
 * Lecturers will be identified as employees who lecture a course.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Employee extends Person {

    private long employeeNumber;

    @Column(nullable = false, unique = true)
    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * Equals by: employeeNumber.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeNumber != employee.employeeNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (employeeNumber ^ (employeeNumber >>> 32));
    }

}
