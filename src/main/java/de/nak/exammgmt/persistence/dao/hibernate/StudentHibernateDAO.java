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
        return getCurrentSession().createSQLQuery("SELECT s.*, last_attempt.passed, last_attempt.ATTEMPT FROM STUDENT s LEFT JOIN (SELECT STUDENT, ATTEMPT, ((GRADE <= 4 AND NOT REEXAMINATION) OR (GRADE <= 3 AND REEXAMINATION)) passed FROM EXAM_PERFORMANCE ep JOIN EXAM e ON e.ID = ep.EXAM WHERE e.COURSE = :course AND NOT REVERSED ORDER BY ep.UPDATED_AT DESC LIMIT 1) last_attempt ON last_attempt.STUDENT = s.ID WHERE s.MANIPLE = :maniple AND (last_attempt.passed IS NULL OR (NOT last_attempt.passed AND last_attempt.ATTEMPT IS NOT NULL AND last_attempt.ATTEMPT < 3))")
                .setParameter("maniple", exam.getCourse().getManiple())
                .setParameter("course", exam.getCourse())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findPossibleReexaminationAttendees(Exam exam) {
        return getCurrentSession().createQuery("SELECT ep.student FROM ExamPerformance ep WHERE ep.exam = :exam AND ep.reexaminationPossible")
                .setParameter("exam", exam)
                .list();
    }

}
