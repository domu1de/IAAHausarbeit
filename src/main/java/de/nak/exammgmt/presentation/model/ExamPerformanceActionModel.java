/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.ExamPerformance;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ExamPerformanceActionModel {

    private ExamPerformance examPerformance;

    public ExamPerformance getExamPerformance() {
        return examPerformance;
    }

    public void setExamPerformance(ExamPerformance examPerformance) {
        this.examPerformance = examPerformance;
    }
}
