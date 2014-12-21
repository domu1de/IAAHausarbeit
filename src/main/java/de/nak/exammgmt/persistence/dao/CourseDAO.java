/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;

import java.util.List;

/**
 * Data Access Object to provide persisted {@link Course} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface CourseDAO extends DAO<Course> {

    /**
     * Finds a list of all courses in the given maniple.
     * ORDERED BY title
     *
     * @param maniple the maniple whose Courses will be searched
     * @return List of all courses in the maniple
     */
    List<Course> findByManiple(Maniple maniple);

}
