/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.persistence.entity.Student;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class WelcomeManagementActionModel {

    private SortedMap<LocalDate, List<Exam>> exams = new TreeMap<>(Comparator.reverseOrder());
    private List<Student> students = new ArrayList<>();
    private List<Maniple> maniples = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public SortedMap<LocalDate, List<Exam>> getExams() {
        return exams;
    }

    public void setExams(SortedMap<LocalDate, List<Exam>> exams) {
        this.exams = exams;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Maniple> getManiples() {
        return maniples;
    }

    public void setManiples(List<Maniple> maniples) {
        this.maniples = maniples;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
