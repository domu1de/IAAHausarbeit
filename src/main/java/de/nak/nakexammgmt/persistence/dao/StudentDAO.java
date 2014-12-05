/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao;

import de.nak.nakexammgmt.persistence.entity.Exam;
import de.nak.nakexammgmt.persistence.entity.Student;

import java.util.List;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface StudentDAO extends DAO<Student> {
    List<Student> findPossibleAttendees(Exam exam);
}
