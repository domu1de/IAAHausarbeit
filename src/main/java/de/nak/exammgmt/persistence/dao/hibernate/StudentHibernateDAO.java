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
 * Hibernate specific implementation of the {@link StudentDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class StudentHibernateDAO extends HibernateDAO<Student> implements StudentDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findPossibleAttendees(Exam exam) {
        /*return getCurrentSession().createQuery(
                "SELECT s " +
                "FROM Student s " +
                "WHERE s.maniple = :maniple " +
                    "AND EXISTS " +
                        // Student has passed the course already
                        "(FROM ExamPerformance " +
                        "WHERE student = s " +
                        "AND exam.course = :course " +
                        "AND NOT reversed " +
                        "AND ((grade <= 4 AND NOT reexamination) OR (grade <= 3 AND reexamination))) " +
                    "AND EXISTS " +
                        // Student had three attempts already
                        "(FROM ExamPerformance " +
                        "WHERE student = s " +
                        "AND exam.course = :course " +
                        "AND NOT reversed " +
                        "AND attempt = 3)")
                .setParameter("maniple", exam.getCourse().getManiple())
                .setParameter("course", exam.getCourse())
                .list();*/
        // Optimized query
        // Reasons: No subselect in HQL-FROM; No limit in HQL
        // Gain: Just one subselect; subselect just executed once and not for every row
        return getCurrentSession().createSQLQuery(
                "SELECT s.*, last_attempt.PASSED, last_attempt.ATTEMPT " +
                        "FROM STUDENT s " +
                        "LEFT JOIN (" +
                        "SELECT ep1.STUDENT, ep1.ATTEMPT, ((ep1.GRADE <= 4 AND NOT ep1.REEXAMINATION) OR (ep1.GRADE <= 3 AND ep1.REEXAMINATION)) PASSED " +
                        "FROM EXAM_PERFORMANCE ep1 " +
                        "LEFT JOIN EXAM_PERFORMANCE ep2 ON (ep1.STUDENT = ep2.STUDENT AND ep1.UPDATED_AT < ep2.UPDATED_AT) " +
                        "JOIN EXAM e ON e.ID = ep1.EXAM " +
                        "WHERE ep2.ID IS NULL AND e.COURSE = :course_id AND NOT ep1.REVERSED " +
                        ") last_attempt ON last_attempt.STUDENT = s.ID " +
                        "WHERE s.MANIPLE = :maniple_id AND " +
                        "(last_attempt.PASSED IS NULL " +
                        "OR (NOT last_attempt.PASSED " +
                        "AND last_attempt.ATTEMPT IS NOT NULL " +
                        "AND last_attempt.ATTEMPT < 3))")
                .addEntity(Student.class)
                .setParameter("maniple_id", exam.getCourse().getManiple().getId())
                .setParameter("course_id", exam.getCourse().getId())
                .list();
        // TODO: criteria api?
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findPossibleReexaminationAttendees(Exam exam) {
        // TODO only twice!
        return getCurrentSession().createQuery("SELECT ep.student FROM ExamPerformance ep WHERE ep.exam = :exam AND ep.reexaminationPossible")
                .setParameter("exam", exam)
                .list();
    }

}
