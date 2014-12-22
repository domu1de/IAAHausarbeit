/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Entity to store an ExamPerformance.
 * Every performance will be stored separately, reexaminations will be marked as such
 * and are not part of the original data set.
 * ExamPerformances cannot be updated or deleted but instead marked as reversed.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class ExamPerformance extends AbstractEntity {

    private Exam exam;
    private Student student;
    private int attempt;
    private float grade;
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
    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    @Column(nullable = false, updatable = false, precision = 2, scale = 1, columnDefinition = "DECIMAL(2, 1)")
    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
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

    @Transient
    public boolean isPassed() {
        return (!reexamination && grade <= 4) || (reexamination && grade <= 3);
    }

    /**
     * Equals by: attempt, reexamination, reversed, exam and student.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamPerformance that = (ExamPerformance) o;

        if (attempt != that.attempt) return false;
        if (reexamination != that.reexamination) return false;
        if (reversed != that.reversed) return false;
        if (exam != null ? !exam.equals(that.exam) : that.exam != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exam != null ? exam.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + attempt;
        result = 31 * result + (reversed ? 1 : 0);
        result = 31 * result + (reexamination ? 1 : 0);
        return result;
    }
}
