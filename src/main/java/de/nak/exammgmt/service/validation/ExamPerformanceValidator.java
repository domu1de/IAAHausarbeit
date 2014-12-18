/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.validation;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;

import java.util.List;

/**
 * An ExamPerformanceValidator is responsible for validating a given {@link ExamPerformance}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ExamPerformanceValidator {

    /**
     * Validates that the given exam performance meets the business rules.
     *
     * @param examPerformance the exam performance to validate
     * @param previousAttempts all previous attempts
     * @throws ExamPerformanceValidationException if the {@see examPerformance} is invalid
     */
    void validate(ExamPerformance examPerformance, List<ExamPerformance> previousAttempts) throws ExamPerformanceValidationException;

}
