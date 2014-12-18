/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

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
    Student get(long studentId) throws NotFoundException;

    /**
     * Lists the current performance per course for the given student.
     *
     * @param studentId the student's id to receive the current performance for
     * @return list of last attempts
     * @throws NotFoundException if an entity could be found
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
