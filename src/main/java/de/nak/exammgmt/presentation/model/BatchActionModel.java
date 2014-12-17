/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class BatchActionModel {

    private List<ExamPerformance> examPerformances = new ArrayList<>();
    private List<Student> possibleAttendees = new ArrayList<>();

    public List<ExamPerformance> getExamPerformances() {
        return examPerformances;
    }

    public void setExamPerformances(List<ExamPerformance> examPerformances) {
        this.examPerformances = examPerformances;
    }

    public List<Student> getPossibleAttendees() {
        return possibleAttendees;
    }

    public void setPossibleAttendees(List<Student> possibleAttendees) {
        this.possibleAttendees = possibleAttendees;
    }
}
