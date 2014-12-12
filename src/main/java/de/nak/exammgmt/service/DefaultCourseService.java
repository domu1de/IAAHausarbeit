/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.CourseDAO;
import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.service.exception.MissingIdException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Default implementation of the {@Link CourseService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultCourseService implements CourseService {

    private CourseDAO courseDAO;
    private ManipleService manipleService;

    @Override
    public Course get(long courseId) throws NotFoundException {
        return null;
    }

    @Override
    public List<Course> list(long manipleId) throws NotFoundException {
        return list(manipleService.get(manipleId));
    }

    @Override
    public List<Course> list(Maniple maniple) {
        if (maniple.getId() == null) {
            throw new MissingIdException();
        }
        // TODO: check if maniple exists, switch?!
        return courseDAO.findByManiple(maniple);
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setManipleService(ManipleService manipleService) {
        this.manipleService = manipleService;
    }
}
