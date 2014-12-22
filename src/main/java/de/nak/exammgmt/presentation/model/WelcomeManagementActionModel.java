/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.Exam;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class WelcomeManagementActionModel {

    private SortedMap<LocalDate, List<Exam>> exams = new TreeMap<>(Comparator.reverseOrder());

    public SortedMap<LocalDate, List<Exam>> getExams() {
        return exams;
    }

    public void setExams(SortedMap<LocalDate, List<Exam>> exams) {
        this.exams = exams;
    }

}
