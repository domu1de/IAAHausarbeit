/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.List;

/**
 * Contract for a service to manage Students.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface StudentService {

    List<Student> listPossibleAttendees(Exam exam);

    List<Student> listPossibleReexaminationAttendees(Exam exam);

}
