/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import de.nak.exammgmt.persistence.entity.user.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Abstract entity for all Persons (currently students and employees) with basic attributes and an optional
 * user reference, so that not every Person in the system needs an active user.
 * This is especially important when students get their degree and their user accounts close.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@MappedSuperclass
public abstract class Person extends AbstractEntity {

    private String title = "";
    private User user;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne(optional = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Transient
    public String getName() {
        return String.join(" ", title, firstName, lastName).trim();
    }

}
