/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage {@link Course} relationships.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface CourseService {

    /**
     * Gets the course for the given id.
     *
     * @param courseId the course id to get the entity for
     * @return the course entity
     * @throws NotFoundException if no entity could be found
     */
    Course get(long courseId) throws NotFoundException;

    /**
     * Lists all courses.
     *
     * @return list of courses
     */
    List<Course> list();

    /**
     * Lists the current performance per student for the given course.
     *
     * @param courseId the course id to get the performances for
     * @return list of current performances per student
     * @throws NotFoundException if no course could be found
     */
    List<ExamPerformance> listCurrentPerformancePerStudent(long courseId) throws NotFoundException;

    /**
     * Lists the current performance per student for the given course.
     *
     * @param course the course to get the performances for
     * @return list of current performances per student
     */
    List<ExamPerformance> listCurrentPerformancePerStudent(Course course);

}
