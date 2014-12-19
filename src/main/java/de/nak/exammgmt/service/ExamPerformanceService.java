/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage ExamPerformances.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ExamPerformanceService {

    /**
     * Persists the given {@link ExamPerformance}.
     *
     * @param examPerformance the exam performance to create
     * @throws NotFoundException if no entity could be found
     * @throws ExamPerformanceValidationException if the given exam performance is invalid
     */
    void create(ExamPerformance examPerformance) throws NotFoundException, ExamPerformanceValidationException;

    /**
     * Initializes the Students of the given exam performances.
     *
     * @param examPerformances list of exam performances
     */
    void initializeStudents(List<ExamPerformance> examPerformances);

}
