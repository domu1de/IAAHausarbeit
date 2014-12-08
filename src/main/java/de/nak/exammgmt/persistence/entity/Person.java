/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends AbstractEntity {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
