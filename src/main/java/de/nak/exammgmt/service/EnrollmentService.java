/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract to manage everything concerning an {@link Enrollment}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface EnrollmentService {

    /**
     * Gets the enrollment for the given student and course.
     *
     * @param studentId id of the student to look for
     * @param courseId id of the course to look for
     * @return the enrollment
     */
    Enrollment getByStudentAndCourse(long studentId, long courseId);

    /**
     * Lists all enrollments of the given student.
     *
     * @param studentId id of the student to look for
     * @return list of all enrollments of the student
     * @throws NotFoundException if no entity could be found
     */
    List<Enrollment> listByStudent(long studentId) throws NotFoundException;

    /**
     * Lists all enrollments of the given student.
     *
     * @param student the student to look for
     * @return list of all enrollments of the student
     */
    List<Enrollment> listByStudent(Student student);

    /**
     * Lists all enrollments of the given course.
     *
     * @param courseId id of the course to look for
     * @return list of all enrollments of the course
     * @throws NotFoundException if no entity could be found
     */
    List<Enrollment> listByCourse(long courseId) throws NotFoundException;

    /**
     * Lists all enrollments of the given course.
     *
     * @param course the course to look for
     * @return list of all enrollments of the course
     */
    List<Enrollment> listByCourse(Course course);

}
