/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity;

import de.nak.nakexammgmt.persistence.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.beans.Transient;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Employee extends AbstractEntity{

    private User user;
    private String title;
    private String firstName;
    private String lastName;

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

    //TODO extract to service.
    public void setName(String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        if (user != null) {
            user.setFullName(title + " " + firstName + " " + lastName);
        }
    }
}
