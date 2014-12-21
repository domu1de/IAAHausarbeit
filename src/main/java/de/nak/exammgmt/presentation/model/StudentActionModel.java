/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem;
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
public class StudentActionModel {

    private Student student;
    private Enrollment enrollment;
    private List<Enrollment> enrollments = new ArrayList<>();
    private Map<ExamPerformance, ExamPerformanceProtocolItem> fullHistory = new LinkedHashMap<>();

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Map<ExamPerformance, ExamPerformanceProtocolItem> getFullHistory() {
        return fullHistory;
    }

    public void setFullHistory(Map<ExamPerformance, ExamPerformanceProtocolItem> fullHistory) {
        this.fullHistory = fullHistory;
    }

    public String gradeToCssClass(float grade) {
        return GradePresenter.toCssClass(grade);
    }
}
