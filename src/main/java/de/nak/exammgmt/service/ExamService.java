/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage Exams.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ExamService {

    void create(Exam exam) throws AlreadyCreatedException;

    void save(Exam exam);

    void saveExamPerformances(long examId, List<ExamPerformance> examPerformances, boolean isReexamination) throws NotFoundException;

    List<Student> listPossibleAttendees(long examId) throws NotFoundException;

    List<Student> listPossibleReexaminationAttendees(long examId) throws NotFoundException;

    Exam get(long examId) throws NotFoundException;
}
