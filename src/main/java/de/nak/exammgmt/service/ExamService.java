/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage {@link Exam} relationships.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ExamService {

    /**
     * Return an exam for the given id.
     *
     * @param examId the id to look for
     * @return the exam
     * @throws NotFoundException if no entity could be found
     */
    Exam get(long examId) throws NotFoundException;

    /**
     * Persists the given exam.
     *
     * @param exam the exam to create
     * @throws AlreadyCreatedException if the entity already exists
     */
    void create(Exam exam) throws AlreadyCreatedException;

    /**
     * Updates the given exam.
     *
     * @param exam the exam to update
     */
    void save(Exam exam);

    /**
     * Saves the given exam performances for the given exam.
     *
     * @param examId the related exam
     * @param examPerformances the exam performances to save
     * @param isReexamination if the given exam performances are reexaminations
     * @throws NotFoundException if no entity could be found
     * @throws ExamPerformanceValidationException if any given exam performance is invalid
     */
    void saveExamPerformances(long examId, List<ExamPerformance> examPerformances, boolean isReexamination) throws NotFoundException, ExamPerformanceValidationException;

    /**
     * Lists all possible attendees for the given exam.
     *
     * @param examId the exam's id to look for
     * @return list of possible attendees
     * @throws NotFoundException if no entity could be found
     */
    List<Student> listPossibleAttendees(long examId) throws NotFoundException;

    /**
     * Lists all possible attendees for the given exam.
     *
     * @param exam the exam to look for
     * @return list of possible attendees
     */
    List<Student> listPossibleAttendees(Exam exam);

    /**
     * Lists all possible reexamination attendees for the given exam.
     *
     * @param examId the exam's id to look for
     * @return list of possible reexamination attendees
     * @throws NotFoundException if no entity could be found
     */
    List<Student> listPossibleReexaminationAttendees(long examId) throws NotFoundException;

    /**
     * Lists all possible reexamination attendees for the given exam.
     *
     * @param exam the exam to look for
     * @return list of possible reexamination attendees
     */
    List<Student> listPossibleReexaminationAttendees(Exam exam);

}
