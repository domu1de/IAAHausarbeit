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
     * TODO NÖTIG?
     * @param examPerformances list of exam performances
     */
    void initializeStudents(List<ExamPerformance> examPerformances);

    /**
     * Reverses the given exam performance and returns the corresponding protocol item.
     *
     * @param examPerformanceId the exam performance id to reverse
     * @return the corresponding protocol item
     * @throws NotFoundException if no entity could be found
     */
    ExamPerformanceProtocolItem reverse(long examPerformanceId) throws NotFoundException, ExamPerformanceValidationException;

    /**
     * Updates the given exam performance to persist the new grade and returns the corresponding protocol item.
     *
     * @param examPerformanceId the exam performance id to update the grade for
     * @param newGrade the new grade
     * @param reexaminationPossible if reexamination is possible for the new grade
     * @return the corresponding protocol item
     * @throws NotFoundException if no entity could be found
     */
    ExamPerformanceProtocolItem updateGrade(long examPerformanceId, float newGrade, boolean reexaminationPossible) throws NotFoundException, ExamPerformanceValidationException;

    /**
     * Lists the full history of exam performances for the given course and student.
     * Reversed performances will be included! Sorted from first to last.
     *
     * @param courseId the course's id to look for
     * @param studentId the student's id to look for
     * @return list of all exam performances (including reversed ones)
     * @throws NotFoundException if no entity could be found
     */
    List<ExamPerformance> listFullHistory(long courseId, long studentId) throws NotFoundException;

    /**
     * Lists the full history of exam performances for the given course and student.
     * Reversed performances will be included! Sorted from first to last.
     *
     * @param course the course to look for
     * @param student the student to look for
     * @return list of all exam performances (including reversed ones)
     */
    List<ExamPerformance> listFullHistory(Course course, Student student);

    /**
     * Returns the protocol item for the given exam performance
     *
     * @param examPerformance the old exam performance in the protocol item
     * @return the protocol item or null
     */
    ExamPerformanceProtocolItem getProtocolForPerformance(ExamPerformance examPerformance);

    /**
     * Returns the current performance for the given student in the given course.
     *
     * @param course the course to look for
     * @param student the student to look for
     * @return the current performance
     * @throws NotFoundException if no entity could be found
     */
    ExamPerformance getCurrentPerformance(Course course, Student student) throws NotFoundException;

}
