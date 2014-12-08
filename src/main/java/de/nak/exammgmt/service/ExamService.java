/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ExamService {

    void create(Exam exam) throws AlreadyCreatedException;

    void save(Exam exam);

    List<Student> listPossibleAttendees(long examId) throws NotFoundException;

    Exam get(long examId) throws NotFoundException;
}
