/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.presentation.GradePresenter;
import de.nak.exammgmt.service.Enrollment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ManipleActionModel {

    private Maniple maniple;
    private List<Maniple> maniples = new ArrayList<>();
    private List<Course> manipleCourses = new ArrayList<>();
    private Map<Student, Map<Course, Enrollment>> manipleEnrollments = new LinkedHashMap<>();

    public List<Maniple> getManiples() {
        return maniples;
    }

    public void setManiples(List<Maniple> maniples) {
        this.maniples = maniples;
    }

    public Map<Student, Map<Course, Enrollment>> getManipleEnrollments() {
        return manipleEnrollments;
    }

    public void setManipleEnrollments(Map<Student, Map<Course, Enrollment>> manipleEnrollments) {
        this.manipleEnrollments = manipleEnrollments;
    }

    public Maniple getManiple() {
        return maniple;
    }

    public void setManiple(Maniple maniple) {
        this.maniple = maniple;
    }

    public List<Course> getManipleCourses() {
        return manipleCourses;
    }

    public void setManipleCourses(List<Course> manipleCourses) {
        this.manipleCourses = manipleCourses;
    }

    public String gradeToCssClass(float grade) {
        return GradePresenter.toCssClass(grade);
    }
}
