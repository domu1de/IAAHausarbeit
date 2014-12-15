/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface CourseService {

    Course get(long courseId) throws NotFoundException;

    List<Course> list(long manipleId) throws NotFoundException;

    List<Course> list(Maniple maniple);

}