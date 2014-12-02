/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Exam extends AbstractEntity{

    private LocalDate date;
    private Set<Employee> lecturers;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToMany
    public Set<Employee> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Employee> lecturers) {
        this.lecturers = lecturers;
    }
}
