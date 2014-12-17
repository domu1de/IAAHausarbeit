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
    public ExamPerformance findLastAttemptByCourseAndStudent(Course course, Student student) {
        return (ExamPerformance) getCurrentSession()
                .createQuery("FROM ExamPerformance WHERE reversed = false AND exam.course = :course AND student = :student ORDER BY updatedAt DESC")
                .setParameter("course", course)
                .setParameter("student", student)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExamPerformance> findAttemptsByCourseAndStudent(Course course, Student student) {
        return getCurrentSession()
                .createQuery("FROM ExamPerformance WHERE reversed = false AND exam.course = :course AND student = :student ORDER BY updatedAt DESC")
                .setParameter("course", course)
                .setParameter("student", student)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExamPerformance> findLastAttemptsByStudent(Student student) {
        return getCurrentSession().createQuery(
                "FROM ExamPerformance ep1 " +
                        "LEFT JOIN ExamPerformance ep2 " +
                        "ON (ep1.student = ep2.student " +
                        "AND ep1.exam.course = ep2.exam.course " +
                        "AND ep1.updatedAt < ep2.updatedAt) " +
                        "WHERE ep2 IS NULL " +
                        "AND ep1.student = :student " +
                        "AND NOT ep1.reversed")
                .setParameter("student", student)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExamPerformance> findCurrentByCourse(Course course) {
        return getCurrentSession().createQuery(
                "FROM ExamPerformance ep1 " +
                        "LEFT JOIN ExamPerformance ep2 " +
                        "ON (ep1.student = ep2.student " +
                        "AND ep1.exam.course = ep2.exam.course " +
                        "AND ep1.updatedAt < ep2.updatedAt) " +
                        "WHERE ep2 IS NULL " +
                        "AND ep1.exam.course = :course " +
                        "AND NOT ep1.reversed")
                .setParameter("course", course)
                .list();
    }

}
