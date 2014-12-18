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
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface EnrollmentService {

    Enrollment getByStudentAndCourse(long studentId, long courseId);

    List<Enrollment> listByStudent(long studentId) throws NotFoundException;

    List<Enrollment> listByStudent(Student student);

    List<Enrollment> listByCourse(long courseId) throws NotFoundException;

    List<Enrollment> listByCourse(Course course);

}
