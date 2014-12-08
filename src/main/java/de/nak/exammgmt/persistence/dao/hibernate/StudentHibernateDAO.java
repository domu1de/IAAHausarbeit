/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.List;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class StudentHibernateDAO extends HibernateDAO<Student> implements StudentDAO {
    @Override
    public List<Student> findPossibleAttendees(Exam exam) {
        return getCurrentSession().createQuery("")
                .setParameter("exam", exam)
                .list();
    }
}