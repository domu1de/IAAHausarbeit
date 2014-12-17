/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link Student} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface StudentDAO extends DAO<Student> {

    /**
     * Finds a List of all Students who can attend the given exam.
     * A student is a possible attendee if he has not passed the course yet or failed it.
     *
     * @param exam the Exam to find possible attendees for
     * @return List of Students who are possible attendees
     */
    List<Student> findPossibleAttendees(Exam exam);

    /**
     * Finds a List of all Students who are allowed to take a reexamination in the given Exam.
     *
     * @param exam the Exam that shall be reexamined
     * @return List of Students that are allowed to reexamine the Exam
     */
    List<Student> findPossibleReexaminationAttendees(Exam exam);
}
