/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.Enrollment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StudentActionModel {

    private Student student;
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

}
