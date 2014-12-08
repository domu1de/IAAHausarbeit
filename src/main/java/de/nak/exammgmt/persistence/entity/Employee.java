/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import de.nak.exammgmt.persistence.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Employee extends Person {

    private User user;
    private String title;

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //TODO extract to service.
    public void setName(String title, String firstName, String lastName) {
        this.title = title;
        setFirstName(firstName);
        setLastName(lastName);
        if (user != null) {
            user.setFullName(title + " " + firstName + " " + lastName);
        }
    }
}
