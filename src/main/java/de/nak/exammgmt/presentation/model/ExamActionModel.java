/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.Exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ExamActionModel {

    private Exam exam;

    private List<String> maniples = new ArrayList<>();
    private Map<String, Map<Long, String>> coursesPerManiple = new HashMap<>();
    private Map<Long, Map<Long, String>> lecturersPerCourse = new HashMap<>();

    private List<Employee> lecturers = new ArrayList<>();

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<String> getManiples() {
        return maniples;
    }

    public void setManiples(List<String> maniples) {
        this.maniples = maniples;
    }

    public Map<String, Map<Long, String>> getCoursesPerManiple() {
        return coursesPerManiple;
    }

    public void setCoursesPerManiple(Map<String, Map<Long, String>> coursesPerManiple) {
        this.coursesPerManiple = coursesPerManiple;
    }

    public void putCourses(String maniple, Map<Long, String> coursesPerManiple) {
        this.coursesPerManiple.put(maniple, coursesPerManiple);
    }

    public Map<Long, Map<Long, String>> getLecturersPerCourse() {
        return lecturersPerCourse;
    }

    public void setLecturersPerCourse(Map<Long, Map<Long, String>> lecturersPerCourse) {
        this.lecturersPerCourse = lecturersPerCourse;
    }

    public void putLecturers(Long courseId, Map<Long, String> lecturersPerCourse) {
        this.lecturersPerCourse.put(courseId, lecturersPerCourse);
    }

    public List<Employee> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Employee> lecturers) {
        this.lecturers = lecturers;
    }
}
