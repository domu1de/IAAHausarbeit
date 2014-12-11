/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.CourseDAO;
import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;

import java.util.List;

/**
 * Hibernate specific implementation of the {@link CourseDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class CourseHibernateDAO extends HibernateDAO<Course> implements CourseDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> findByManiple(Maniple maniple) {
        return getCurrentSession().createQuery("FROM Course c JOIN FETCH c.lecturers WHERE c.maniple = :maniple")
                .setParameter("maniple", maniple)
                .list();
    }

}
