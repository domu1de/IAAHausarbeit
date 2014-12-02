/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity;

import de.nak.nakexammgmt.persistence.entity.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Student extends AbstractEntity {

    private Integer studentId;
    private User user;
    private Maniple maniple;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Maniple getManiple() {
        return maniple;
    }

    public void setManiple(Maniple maniple) {
        this.maniple = maniple;
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
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (user != null) {
            user.setFullName(firstName + " " + lastName);
        }
    }
}
