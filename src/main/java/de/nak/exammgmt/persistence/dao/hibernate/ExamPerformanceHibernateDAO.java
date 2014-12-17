/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;

import java.util.List;

/**
 * Hibernate specific implementation of the {@link ExamPerformanceDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ExamPerformanceHibernateDAO extends HibernateDAO<ExamPerformance> implements ExamPerformanceDAO {

    @Override
    // TODO nötig?
    public ExamPerformance findLastAttempt(Course course, Student student) {
        return (ExamPerformance) getCurrentSession()
                .createQuery("FROM ExamPerformance WHERE reversed = false AND exam.course = :course AND student = :student ORDER BY updatedAt DESC")
                .setParameter("course", course)
                .setParameter("student", student)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExamPerformance> findAttempts(Course course, Student student) {
        return getCurrentSession()
                .createQuery("FROM ExamPerformance WHERE reversed = false AND exam.course = :course AND student = :student ORDER BY updatedAt DESC")
                .setParameter("course", course)
                .setParameter("student", student)
                .list();
    }

}
