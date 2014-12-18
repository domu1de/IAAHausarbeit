/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CourseActionModel {

    private Course course;
    private List<ExamPerformance> currentPerformances = new ArrayList<>();

    public List<ExamPerformance> getCurrentPerformances() {
        return currentPerformances;
    }

    public void setCurrentPerformances(List<ExamPerformance> currentPerformances) {
        this.currentPerformances = currentPerformances;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
