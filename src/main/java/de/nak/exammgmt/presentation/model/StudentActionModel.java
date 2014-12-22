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

import java.util.*;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StudentActionModel {

    private Student student;
    private Enrollment enrollment;
    private List<Enrollment> enrollments = new ArrayList<>();
    private SortedMap<Integer, List<ExamPerformanceWithProtocolItem>> fullHistory = new TreeMap<>(Comparator.reverseOrder());

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

    public SortedMap<Integer, List<ExamPerformanceWithProtocolItem>> getFullHistory() {
        return fullHistory;
    }

    public void setFullHistory(SortedMap<Integer, List<ExamPerformanceWithProtocolItem>> fullHistory) {
        this.fullHistory = fullHistory;
    }

    public String gradeToCssClass(float grade) {
        return GradePresenter.toCssClass(grade);
    }

    public static class ExamPerformanceWithProtocolItem {

        private ExamPerformance examPerformance;
        private ExamPerformanceProtocolItem protocolItem;

        public ExamPerformanceWithProtocolItem(ExamPerformance examPerformance, ExamPerformanceProtocolItem protocolItem) {
            this.examPerformance = examPerformance;
            this.protocolItem = protocolItem;
        }

        public ExamPerformance getExamPerformance() {
            return examPerformance;
        }


        public ExamPerformanceProtocolItem getProtocolItem() {
            return protocolItem;
        }

    }

}
