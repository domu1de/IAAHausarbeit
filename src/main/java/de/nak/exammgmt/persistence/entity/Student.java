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

    private int studentId;
    private Maniple maniple;

    @Column(unique = true, nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @ManyToOne(optional = false)
    public Maniple getManiple() {
        return maniple;
    }

    public void setManiple(Maniple maniple) {
        this.maniple = maniple;
    }

    /**
     * Equals by: studentId.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != student.studentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return studentId;
    }
}
