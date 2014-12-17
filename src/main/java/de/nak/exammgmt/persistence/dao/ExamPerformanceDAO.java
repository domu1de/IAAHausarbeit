/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link ExamPerformance} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ExamPerformanceDAO extends DAO<ExamPerformance> {

    /**
     * Finds the last, unreversed attempt for the given course and student.
     *
     * @param course the course to look for
     * @param student the student to look for
     * @return the last attempt, or null
     */
    ExamPerformance findLastAttemptByCourseAndStudent(Course course, Student student);

    /**
     * Finds all unreversed attempts for the given course and student.
     * The result is sorted (newest to oldest).
     *
     * @param course the course to look for
     * @param student the student to look for
     * @return list of attempts
     */
    List<ExamPerformance> findAttemptsByCourseAndStudent(Course course, Student student);

    /**
     * Finds the last unreversed attempts of the student for each course.
     *
     * @param student the student to look for
     * @return list of last attempts
     */
    List<ExamPerformance> findLastAttemptsByStudent(Student student);

    List<ExamPerformance> findCurrentByCourse(Course course);

}
