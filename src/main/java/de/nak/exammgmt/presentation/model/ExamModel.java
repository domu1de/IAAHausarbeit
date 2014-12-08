/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ExamModel {

    private Exam exam;
    private List<Maniple> maniples = new ArrayList<>();
    private Map<Long, List<Course>> manipleCourses = new HashMap<>();

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Maniple> getManiples() {
        return maniples;
    }

    public void setManiples(List<Maniple> maniples) {
        this.maniples = maniples;
    }

    public void putManipleCourse(long manipleId, List<Course> courses) {
        manipleCourses.put(manipleId, courses);
    }

    public Map<Long, List<Course>> getManipleCourses() {
        return manipleCourses;
    }

    public void setManipleCourses(Map<Long, List<Course>> manipleCourses) {
        this.manipleCourses = manipleCourses;
    }
}
