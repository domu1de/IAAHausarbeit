/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.authentication.AuthenticationService;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Default implementation of the {@link ExamPerformanceService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultExamPerformanceService implements ExamPerformanceService {

    private ExamPerformanceDAO examPerformanceDAO;
    private AuthenticationService authenticationService;
    private EmployeeService employeeService;
    private StudentService studentService;
    private CourseService courseService;

    @Override
    public void create(ExamPerformance examPerformance) throws NotFoundException {
        List<ExamPerformance> attempts = examPerformanceDAO.findAttemptsByCourseAndStudent(examPerformance.getExam().getCourse(), examPerformance.getStudent());
        ExamPerformance lastAttempt = !attempts.isEmpty() ? attempts.get(0) : null;

        if (lastAttempt != null && lastAttempt.isPassed()) {
            // TODO: throw new exception
        }

        setAttempt(examPerformance, lastAttempt);

        if (examPerformance.getAttempt() > 3) {
            // TODO throw exception
        }

        if (examPerformance.isReexamination()) {
            if (attempts.isEmpty()) {
                throw new RuntimeException();
                // TODO exception
            }

            if (lastAttempt != null && lastAttempt.isReexamination()) {
                // TODO exception
            }

            if (lastAttempt != null && !lastAttempt.isReexaminationPossible()) {
                // TODO exception
            }

            if (attempts.stream().filter(ExamPerformance::isReexamination).count() >= 2) {
                // TODO exception
            }
        }

        // TODO get employee
        examPerformance.setCreator(employeeService.get(authenticationService.getCurrentUser()));

        examPerformanceDAO.save(examPerformance);
    }

    @Override
    // TODO: listCourseResults vllt besser?
    public List<ExamPerformance> listLastAttempts(long studentId) throws NotFoundException {
        Student student = studentService.get(studentId); // FIXME: oder nur in object wrappen?
        return examPerformanceDAO.findLastAttemptsByStudent(student);
    }

    @Override
    public List<ExamPerformance> listCurrentPerformances(long courseId) throws NotFoundException {
        Course course = courseService.get(courseId);
        return examPerformanceDAO.findCurrentByCourse(course);
    }

    private void setAttempt(ExamPerformance examPerformance, ExamPerformance lastAttempt) {
        // FIXME, mach mich hübsch!
        if (lastAttempt != null) {
            if (examPerformance.isReexamination()) {
                examPerformance.setAttempt(lastAttempt.getAttempt());
            } else {
                examPerformance.setAttempt(lastAttempt.getAttempt() + 1);
            }
        } else {
            examPerformance.setAttempt(1);
        }
    }

    public void setExamPerformanceDAO(ExamPerformanceDAO examPerformanceDAO) {
        this.examPerformanceDAO = examPerformanceDAO;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
