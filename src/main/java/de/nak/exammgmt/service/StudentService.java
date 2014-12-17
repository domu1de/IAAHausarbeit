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

    /**
     * Gets the student with the given id.
     *
     * @param studentId the id of the student
     * @return the student
     */
    Student get(Long studentId);

    /**
     * Lists all possible attendees for the given exam.
     * A possible attendee is every student in the same maniple as the exams course
     * who has not yet passed the course or failed it.
     *
     * @param exam the exam to look for
     * @return list of possible attendees
     */
    List<Student> listPossibleAttendees(Exam exam);

    /**
     * Lists all possible attendees to reexamine the given exam.
     * A possible reexamination attendee is every student who attempted the exam before but failed it with
     * the possibility for a reexamination.
     * Students who already had two reexaminations are not allowed to attend another one.
     *
     * @param exam the exam to look for
     * @return list of possible reexamination attendees
     */
    List<Student> listPossibleReexaminationAttendees(Exam exam);

}
