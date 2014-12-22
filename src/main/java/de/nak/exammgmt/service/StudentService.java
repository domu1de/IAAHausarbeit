/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage everything concerning {@link Student}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface StudentService {

    /**
     * Gets the student with the given id.
     *
     * @param studentId the id of the student
     * @return the student
     * @throws NotFoundException if no entity could be found
     */
    Student get(long studentId) throws NotFoundException;

    /**
     * Gets the student for the given user.
     *
     * @param user the associated user
     * @return the student
     * @throws NotFoundException if no entity could be found
     */
    Student getByUser(User user) throws NotFoundException;

    /**
     * Lists all students.
     *
     * @return list of all students
     */
    List<Student> list();

    /**
     * Lists the current performance per course for the given student.
     *
     * @param studentId the student's id to receive the current performance for
     * @return list of last attempts
     * @throws NotFoundException if no entity could be found
     */
    List<ExamPerformance> listCurrentPerformancePerCourse(long studentId) throws NotFoundException;

    /**
     * Lists the current performance per course for the given student.
     *
     * @param student the student to receive the current performance for
     * @return list of last attempts
     */
    List<ExamPerformance> listCurrentPerformancePerCourse(Student student);

}
