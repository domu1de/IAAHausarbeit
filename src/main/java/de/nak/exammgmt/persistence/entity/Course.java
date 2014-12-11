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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(nullable = false), inverseJoinColumns = @JoinColumn(nullable = false))
    public Set<Employee> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Employee> lecturers) {
        this.lecturers = lecturers;
    }
}
