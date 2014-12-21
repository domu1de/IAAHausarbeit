/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage everything concerning a {@link Maniple}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ManipleService {

    /**
     * Gets the maniple with the given id.
     *
     * @param manipleId the id of the maniple to get
     * @return the maniple
     * @throws NotFoundException if no entity could be found
     */
    Maniple get(long manipleId) throws NotFoundException;

    /**
     * Lists all maniples in the system.
     *
     * @return list of all maniples
     */
    List<Maniple> list();

    /**
     * Lists all courses in the given maniple.
     *
     * @param manipleId id of the maniple to search with
     * @return list of all courses in the maniple
     * @throws NotFoundException if no entity could be found
     */
    List<Course> listCourses(long manipleId) throws NotFoundException;

    /**
     * Lists all courses in the given maniple.
     *
     * @param maniple the maniple to search with
     * @return list of all courses in the maniple
     */
    List<Course> listCourses(Maniple maniple);

    /**
     * Lists all students in the given maniple.
     *
     * @param manipleId id of the maniple to search with
     * @return list of all students in the maniple
     * @throws NotFoundException if no entity could be found
     */
    List<Student> listStudents(long manipleId) throws NotFoundException;

    /**
     * Lists all students in the given maniple.
     *
     * @param maniple the maniple to search with
     * @return list of all student in the maniple
     */
    List<Student> listStudents(Maniple maniple);


}
