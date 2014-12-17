/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage ExamPerformances.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ExamPerformanceService {

    void create(ExamPerformance examPerformance) throws NotFoundException;

    /**
     * Lists the last unreversed attempts of the given student for each course.
     *
     * @param studentId id of the student to look for
     * @return list of last attempts
     */
    List<ExamPerformance> listLastAttempts(long studentId) throws NotFoundException;

    /**
     * Lists the current performances for the given course.
     *
     * @param courseId id of the course to look for
     * @return list of current performances
     */
    List<ExamPerformance> listCurrentPerformances(long courseId) throws NotFoundException;

}
