/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.service.authentication.AuthenticationService;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.service.validation.ExamPerformanceValidator;

import java.util.List;

/**
 * Default implementation of the {@link ExamPerformanceService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultExamPerformanceService implements ExamPerformanceService {

    private ExamPerformanceDAO examPerformanceDAO;
    private ExamPerformanceValidator examPerformanceValidator;
    private StudentDAO studentDAO;

    private AuthenticationService authenticationService;
    private EmployeeService employeeService;

    @Override
    public void create(ExamPerformance examPerformance) throws NotFoundException, ExamPerformanceValidationException {
        List<ExamPerformance> previousAttempts = examPerformanceDAO.findAttemptsByCourseAndStudent(examPerformance.getExam().getCourse(), examPerformance.getStudent());
        ExamPerformance lastAttempt = !previousAttempts.isEmpty() ? previousAttempts.get(0) : null;

        examPerformanceValidator.validate(examPerformance, previousAttempts);

        setAttempt(examPerformance, lastAttempt);

        if (examPerformance.isReexamination()) {
            examPerformance.setReexaminationPossible(false);
        }
        // TODO: exception?
        if (examPerformance.getGrade() != 5.0f) {
            examPerformance.setReexaminationPossible(false);
        }

        examPerformance.setCreator(employeeService.get(authenticationService.getCurrentUser()));

        examPerformanceDAO.save(examPerformance);
    }

    @Override
    public void initializeStudents(List<ExamPerformance> examPerformances) {
        examPerformances.forEach(ep -> ep.setStudent(studentDAO.findById(ep.getStudent().getId())));
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

    public void setExamPerformanceValidator(ExamPerformanceValidator examPerformanceValidator) {
        this.examPerformanceValidator = examPerformanceValidator;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
}
