/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class ExamPerformance extends AbstractEntity {

    private Exam exam;
    private Student student;
    private Course course;
    private Byte attempt;
    private Float grade;
    private boolean reversed = false;
    private Employee creator;
    private boolean reexamination = false;
    private boolean reexaminationPossible = false;

    @Column(nullable = false, updatable = false)
    public boolean isReexamination() {
        return reexamination;
    }

    public void setReexamination(boolean reexamination) {
        this.reexamination = reexamination;
    }

    @Column(nullable = false, updatable = false)
    public boolean isReexaminationPossible() {
        return reexaminationPossible;
    }

    public void setReexaminationPossible(boolean reexaminationPossible) {
        this.reexaminationPossible = reexaminationPossible;
    }

    @Column(nullable = false, updatable = false)
    public Byte getAttempt() {
        return attempt;
    }

    public void setAttempt(Byte attempt) {
        this.attempt = attempt;
    }

    @Column(nullable = false, updatable = false)
    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Column(nullable = false, updatable = true)
    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reverted) {
        this.reversed = reverted;
    }

    @ManyToOne(optional = false)
    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    @ManyToOne(optional = false)
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @ManyToOne(optional = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(optional = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
