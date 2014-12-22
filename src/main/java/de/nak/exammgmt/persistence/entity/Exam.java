/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity to store an Exam.
 * Reexaminations will not be stored separately, but identified through the ExamPerformance.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Exam extends AbstractEntity {

    private LocalDate date;
    private Course course;
    private Set<Employee> lecturers = new HashSet<>();

    @Column(nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Employee> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Employee> lecturers) {
        this.lecturers = lecturers;
    }

    @ManyToOne(optional = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Equals by: course, date and lecturers.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;

        if (course != null ? !course.equals(exam.course) : exam.course != null) return false;
        if (date != null ? !date.equals(exam.date) : exam.date != null) return false;
        if (lecturers != null ? !lecturers.equals(exam.lecturers) : exam.lecturers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (lecturers != null ? lecturers.hashCode() : 0);
        return result;
    }

}
