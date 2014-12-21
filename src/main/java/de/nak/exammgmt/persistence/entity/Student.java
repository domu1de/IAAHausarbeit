/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entity to store a Student.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Student extends Person {

    private Integer studentId;
    private Maniple maniple;

    @Column(unique = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @ManyToOne
    public Maniple getManiple() {
        return maniple;
    }

    public void setManiple(Maniple maniple) {
        this.maniple = maniple;
    }

    //TODO extract to service.
    public void setName(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        if (getUser() != null) {
            getUser().setFullName(firstName + " " + lastName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return studentId != null ? studentId.hashCode() : 0;
    }
}
