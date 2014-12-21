/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Student;

/**
 * Enrollments are used to represent a {@link Student}'s current performance (grade) in a certain {@link Course}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class Enrollment {

    private Student student;
    private Course course;
    private Float grade;
    private boolean reexaminationPossible;
    private Integer attempt;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public boolean isReexaminationPossible() {
        return reexaminationPossible;
    }

    public void setReexaminationPossible(boolean reexaminationPossible) {
        this.reexaminationPossible = reexaminationPossible;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

}
