/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem;
import de.nak.exammgmt.persistence.entity.Student;
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

    /**
     * Reverses the given exam performance and returns the corresponding protocol item.
     *
     * @param examPerformance the exam performance to reverse
     * @return the corresponding protocol item
     */
    ExamPerformanceProtocolItem reverse(ExamPerformance examPerformance) throws NotFoundException;

    /**
     * Updates the given exam performance to persist the new grade and returns the corresponding protocol item.
     *
     * @param examPerformance the exam performance to update the grade for
     * @return the corresponding protocol item
     */
    ExamPerformanceProtocolItem updateGrade(ExamPerformance examPerformance) throws NotFoundException;

    /**
     * Lists the full history of exam performances for the given course and student.
     * Reversed performances will be included! Sorted from first to last.
     *
     * @param course the course to look for
     * @param student the student to look for
     * @return list of all exam performances (including reversed ones)
     */
    List<ExamPerformance> listFullHistory(Course course, Student student);

}
