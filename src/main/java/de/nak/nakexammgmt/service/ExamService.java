/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service;

import de.nak.nakexammgmt.persistence.entity.Exam;
import de.nak.nakexammgmt.persistence.entity.Student;
import de.nak.nakexammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ExamService {

    void create(Exam exam);

    void save(Exam exam);

    List<Student> listPossibleAttendees(long examID) throws NotFoundException;


}
