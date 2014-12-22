/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity to store a Course.
 * Every Course refers strictly to one maniple and has a set of (possible) employees to lecture it.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Course extends AbstractEntity {

    private String courseId;
    private String title;
    private String description;
    private Maniple maniple;
    private Set<Employee> lecturers = new HashSet<>();

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public Maniple getManiple() {
        return maniple;
    }

    public void setManiple(Maniple maniple) {
        this.maniple = maniple;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(nullable = false), inverseJoinColumns = @JoinColumn(nullable = false))
    public Set<Employee> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Employee> lecturers) {
        this.lecturers = lecturers;
    }

    /**
     * Equals by: maniple and title.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (maniple != null ? !maniple.equals(course.maniple) : course.maniple != null) return false;
        if (title != null ? !title.equals(course.title) : course.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (maniple != null ? maniple.hashCode() : 0);
        return result;
    }

    @Column(nullable = false)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
