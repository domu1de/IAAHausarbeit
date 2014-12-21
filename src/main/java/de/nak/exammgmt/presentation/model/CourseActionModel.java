/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.service.Enrollment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CourseActionModel {

    private Course course;
    private List<Enrollment> enrollments = new ArrayList<>();
    private Map<Float, Long> gradeCount = new LinkedHashMap<>();
    private double averageGrade;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Map<Float, Long> getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(Map<Float, Long> gradeCount) {
        this.gradeCount = gradeCount;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
