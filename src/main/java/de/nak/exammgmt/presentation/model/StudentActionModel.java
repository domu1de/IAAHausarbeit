/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.ExamPerformance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StudentActionModel {

    List<ExamPerformance> lastAttempts = new ArrayList<>();

    public List<ExamPerformance> getLastAttempts() {
        return lastAttempts;
    }

    public void setLastAttempts(List<ExamPerformance> lastAttempts) {
        this.lastAttempts = lastAttempts;
    }
}
