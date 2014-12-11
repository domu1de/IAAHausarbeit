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

    //TODO extract to service.
    public void setName(String title, String firstName, String lastName) {
        setTitle(title);
        setFirstName(firstName);
        setLastName(lastName);
        if (getUser() != null) {
            getUser().setFullName(title + " " + firstName + " " + lastName);
        }
    }

    @Column(nullable = false, unique = true)
    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
