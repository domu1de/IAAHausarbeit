/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;
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
        // Optimized query (old one not finished properly, just to show the difference)
        // Reasons: No subselect in HQL-FROM; No limit in HQL
        // Gain: Just one subselect; subselect just executed once and not for every row
        return getCurrentSession().createSQLQuery(
                "SELECT s.*, last_attempt.PASSED, last_attempt.ATTEMPT " +
                "FROM STUDENT s " +
                    "LEFT JOIN (" +
                        // Finds the last attempts for the students in the same course and evaluates if they passed with it
                        "SELECT ep1.STUDENT, ep1.ATTEMPT, ep1.EXAM, ((ep1.GRADE <= 4 AND NOT ep1.REEXAMINATION) OR (ep1.GRADE <= 3 AND ep1.REEXAMINATION)) PASSED " +
                        "FROM EXAM_PERFORMANCE ep1 " +
                            "LEFT JOIN EXAM_PERFORMANCE ep2 ON (ep1.STUDENT = ep2.STUDENT AND ep1.UPDATED_AT < ep2.UPDATED_AT) " +
                            "JOIN EXAM e ON e.ID = ep1.EXAM " +
                        "WHERE ep2.ID IS NULL AND e.COURSE = :course_id AND NOT ep1.REVERSED " +
                    ") last_attempt ON last_attempt.STUDENT = s.ID " +
                "WHERE s.MANIPLE = :maniple_id " +
                    // If there was a last attempt, further checking is required
                    "AND (last_attempt.PASSED IS NULL " +
                        // Not allowed to attempt again if already passed
                        "OR (NOT last_attempt.PASSED " +
                        // No second attempt for the the exam
                        "AND NOT last_attempt.EXAM = :exam_id " +
                        // Max of three attempts
                        "AND last_attempt.ATTEMPT IS NOT NULL " +
                        "AND last_attempt.ATTEMPT < 3))")
                .addEntity(Student.class)
                .setLong("maniple_id", exam.getCourse().getManiple().getId())
                .setLong("course_id", exam.getCourse().getId())
                .setLong("exam_id", exam.getId())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findPossibleReexaminationAttendees(Exam exam) {
        // TODO: comment
        return getCurrentSession().createSQLQuery(
                "SELECT s.* " +
                        "FROM STUDENT s " +
                        "  JOIN EXAM_PERFORMANCE ep1 ON (" +
                        "    ep1.STUDENT = s.ID " +
                        "    AND NOT ep1.REVERSED " +
                        "    AND ep1.EXAM = :exam_id " +
                        "    AND ep1.REEXAMINATION_POSSIBLE " +
                        "  )" + // Up until here: only students in the list who could theoretically be allowed to attempt (reexamination is possible)
                        "  LEFT JOIN (" +
                        // Select for every student who ever took a reexamination how many it were in total and in the given exam
                        "    SELECT ep2.STUDENT, SUM(CASE WHEN EXAM = :exam_id THEN 1 ELSE 0 END) EXAM_REEX_COUNT, COUNT(ep2.STUDENT) COURSE_REEX_COUNT" +
                        "    FROM EXAM_PERFORMANCE ep2" +
                        "      JOIN EXAM e ON e.ID = ep2.EXAM" +
                        "    WHERE NOT ep2.REVERSED AND e.COURSE = :course_id AND ep2.REEXAMINATION" +
                        "    GROUP BY ep2.STUDENT" +
                        "  ) rc ON rc.STUDENT = s.ID " +
                        "WHERE rc.STUDENT IS NULL " +
                        // Only students allowed who didn't reexamine this exam before and who didn't reexamine the course twice already
                        "  OR (rc.EXAM_REEX_COUNT = 0 AND COURSE_REEX_COUNT < 2)")
                .addEntity(Student.class)
                .setLong("exam_id", exam.getId())
                .setLong("course_id", exam.getCourse().getId())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByManiple(Maniple maniple) {
        return getCurrentSession().createQuery("FROM Student WHERE maniple = :maniple")
                .setParameter("maniple", maniple)
                .list();
    }

}
