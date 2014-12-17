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
        return getCurrentSession().createSQLQuery(
                "SELECT ep1.* " +
                "FROM EXAM_PERFORMANCE ep1 " +
                    "JOIN EXAM e1 ON (e1.ID = ep1.EXAM) " +
                    "LEFT JOIN (" +
                        "SELECT ep2.ID, ep2.STUDENT, ep2.UPDATED_AT, e2.COURSE " +
                        "FROM EXAM_PERFORMANCE ep2 " +
                            "JOIN EXAM e2 ON ep2.EXAM = e2.ID " +
                        "WHERE NOT ep2.REVERSED" +
                        ") ep3 ON (ep1.STUDENT = ep3.STUDENT AND ep1.UPDATED_AT < ep3.UPDATED_AT AND e1.COURSE = ep3.COURSE) " +
                "WHERE NOT ep1.REVERSED " +
                    "AND ep3.ID IS NULL " +
                    "AND ep1.STUDENT = :student_id")
                .addEntity(ExamPerformance.class)
                .setLong("student_id", student.getId())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExamPerformance> findCurrentByCourse(Course course) {
        return getCurrentSession().createSQLQuery(
                "SELECT ep1.* " +
                "FROM EXAM_PERFORMANCE ep1 " +
                    "JOIN EXAM e1 ON (e1.ID = ep1.EXAM) " +
                    "LEFT JOIN (" +
                        "SELECT ep2.ID, ep2.STUDENT, ep2.UPDATED_AT, e2.COURSE " +
                        "FROM EXAM_PERFORMANCE ep2 " +
                            "JOIN EXAM e2 ON ep2.EXAM = e2.ID " +
                        "WHERE NOT ep2.REVERSED" +
                    ") ep3 ON (ep1.STUDENT = ep3.STUDENT AND ep1.UPDATED_AT < ep3.UPDATED_AT AND e1.COURSE = ep3.COURSE) " +
                "WHERE NOT ep1.REVERSED " +
                    "AND ep3.ID IS NULL " +
                    "AND e1.COURSE = :course_id")
                .addEntity(ExamPerformance.class)
                .setLong("course_id", course.getId())
                .list();
    }

}
