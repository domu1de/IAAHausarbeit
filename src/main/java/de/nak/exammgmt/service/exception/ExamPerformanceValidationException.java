/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

import de.nak.exammgmt.persistence.entity.ExamPerformance;

/**
 * Exception to indicate failures during {@link ExamPerformance} validation.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ExamPerformanceValidationException extends Exception implements TransactionSensitiveException {

    private ExamPerformance examPerformance;

    public ExamPerformanceValidationException(ExamPerformance examPerformance, String message) {
        super(message);
        this.examPerformance = examPerformance;
    }

    public ExamPerformance getExamPerformance() {
        return examPerformance;
    }
}
