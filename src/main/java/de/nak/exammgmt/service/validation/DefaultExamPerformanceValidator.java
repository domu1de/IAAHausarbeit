/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.validation;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;

import java.util.List;

/**
 * Default implementation of {@link ExamPerformanceValidator}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultExamPerformanceValidator implements ExamPerformanceValidator {

    private static final float[] POSSIBLE_GRADES = {1.0f, 1.3f, 1.7f, 2.0f, 2.3f, 2.7f, 3.0f, 3.3f, 3.7f, 4.0f, 5.0f};

    @Override
    public void validate(ExamPerformance examPerformance, List<ExamPerformance> previousAttempts) throws ExamPerformanceValidationException {
        ExamPerformance lastAttempt = !previousAttempts.isEmpty() ? previousAttempts.get(0) : null;

        gradeIsValid(examPerformance);
        lastAttemptNotPassed(examPerformance, lastAttempt);

        if (examPerformance.isReexamination()) {
            reexaminationAllowed(examPerformance, lastAttempt, previousAttempts);
        } else {
            attemptAllowed(examPerformance, lastAttempt);
        }
    }

    /**
     * Validates that the given exam performance's grade is valid.
     *
     * @param attempt the attempt to validate
     * @throws ExamPerformanceValidationException if the {@see attempt} is invalid
     */
    private void gradeIsValid(ExamPerformance attempt) throws ExamPerformanceValidationException {
        for (float possibleGrade : POSSIBLE_GRADES) {
            if (possibleGrade == attempt.getGrade()) {
                return;
            }
        }

        throw new ExamPerformanceValidationException(attempt, "exception.examPerformance.invalidGrade");
    }

    /**
     * Validates that the last attempt hasn't been passed.
     *
     * @param attempt the attempt to validate
     * @param lastAttempt the last attempt
     * @throws ExamPerformanceValidationException if the {@see attempt} is invalid
     */
    private void lastAttemptNotPassed(ExamPerformance attempt, ExamPerformance lastAttempt) throws ExamPerformanceValidationException {
        if (lastAttempt != null && lastAttempt.isPassed()) {
            throw new ExamPerformanceValidationException(attempt, "exception.examPerformance.lastAttemptPassed");
        }
    }

    /**
     * Validates that the given attempt is allowed.
     *
     * @param attempt the attempt to validate
     * @param lastAttempt the last attempt
     * @throws ExamPerformanceValidationException if the {@see attempt} is invalid
     */
    private void attemptAllowed(ExamPerformance attempt, ExamPerformance lastAttempt) throws ExamPerformanceValidationException {
        if (lastAttempt != null && lastAttempt.getAttempt() == 3) {
            throw new ExamPerformanceValidationException(attempt, "exception.examPerformance.attemptNotAllowed");
        }
    }

    /**
     * Validates that the given reexamination is allowed.
     *
     * @param reexamination the reexamination to validate
     * @param lastAttempt the last attempt
     * @param previousAttempts all previous attempts
     * @throws ExamPerformanceValidationException if the {@see reexamination} is invalid
     */
    private void reexaminationAllowed(ExamPerformance reexamination, ExamPerformance lastAttempt, List<ExamPerformance> previousAttempts) throws ExamPerformanceValidationException {
        if (lastAttempt == null) {
            throw new ExamPerformanceValidationException(reexamination, "exception.examPerformance.noPreviousAttempt");
        }

        if (lastAttempt.isReexamination()) {
            throw new ExamPerformanceValidationException(reexamination, "exception.examPerformance.previousAttemptWasReexamination");
        }

        if (!lastAttempt.isReexaminationPossible()) {
            throw new ExamPerformanceValidationException(reexamination, "exception.examPerformance.reeaxaminationNotPossible");
        }

        if (previousAttempts.stream().filter(ExamPerformance::isReexamination).count() >= 2) {
            throw new ExamPerformanceValidationException(reexamination, "exception.examPerformance.tooManyReexaminations");
        }
    }

}
