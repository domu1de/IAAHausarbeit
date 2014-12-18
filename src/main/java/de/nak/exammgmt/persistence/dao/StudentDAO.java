/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link Student} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface StudentDAO extends DAO<Student> {

    /**
     * Finds a list of all students who can attend the given exam.
     * A student is a possible attendee if he has not passed the course yet or failed it.
     *
     * @param exam the exam to find possible attendees for
     * @return list of students who are possible attendees
     */
    List<Student> findPossibleAttendees(Exam exam);

    /**
     * Finds a list of all students who are allowed to take a reexamination in the given exam.
     * A student is a possible attendee if he failed the exam with the possibility of a reexamination and if he
     * did not attempt two reexaminations before for the course of the exam.
     *
     * @param exam the exam that shall be reexamined
     * @return list of students that are allowed to reexamine the exam
     */
    List<Student> findPossibleReexaminationAttendees(Exam exam);

    /**
     * Finds a list of all students for the given maniple.
     *
     * @param maniple the maniple query with
     * @return list of students of the given maniple
     */
    List<Student> findByManiple(Maniple maniple);
}
