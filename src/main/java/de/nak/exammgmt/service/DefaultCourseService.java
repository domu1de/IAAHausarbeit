/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.CourseDAO;
import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Default implementation of the {@link CourseService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultCourseService implements CourseService {

    private CourseDAO courseDAO;
    private ExamPerformanceDAO examPerformanceDAO;

    @Override
    public Course get(long courseId) throws NotFoundException {
        Course course = courseDAO.findById(courseId);
        if (course == null) {
            throw new NotFoundException(Course.class);
        }
        return course;
    }

    @Override
    public List<ExamPerformance> listCurrentPerformancePerStudent(long courseId) throws NotFoundException {
        Course course = get(courseId);
        return listCurrentPerformancePerStudent(course);
    }

    @Override
    public List<ExamPerformance> listCurrentPerformancePerStudent(Course course) {
        // TODO not null
        return examPerformanceDAO.findCurrentByCourse(course);
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setExamPerformanceDAO(ExamPerformanceDAO examPerformanceDAO) {
        this.examPerformanceDAO = examPerformanceDAO;
    }
}
