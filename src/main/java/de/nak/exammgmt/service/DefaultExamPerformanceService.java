/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.dao.ExamPerformanceProtocolItemDAO;
import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.*;
import de.nak.exammgmt.service.authentication.AuthenticationService;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.service.validation.ExamPerformanceValidator;

import java.util.List;
import java.util.Objects;

import static de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem.Type.EDIT;
import static de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem.Type.REVERSAL;

/**
 * Default implementation of {@link ExamPerformanceService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultExamPerformanceService implements ExamPerformanceService {

    private ExamPerformanceDAO examPerformanceDAO;
    private ExamPerformanceValidator examPerformanceValidator;
    private StudentDAO studentDAO;
    private ExamPerformanceProtocolItemDAO examPerformanceProtocolItemDAO;

    private AuthenticationService authenticationService;
    private EmployeeService employeeService;

    @Override
    public void create(ExamPerformance examPerformance) throws NotFoundException, ExamPerformanceValidationException {
        Objects.requireNonNull(examPerformance);

        List<ExamPerformance> previousAttempts = examPerformanceDAO.findAttemptsByCourseAndStudent(examPerformance.getExam().getCourse(), examPerformance.getStudent());
        ExamPerformance lastAttempt = !previousAttempts.isEmpty() ? previousAttempts.get(0) : null;

        examPerformanceValidator.validate(examPerformance, previousAttempts);

        setAttempt(examPerformance, lastAttempt);

        if (examPerformance.isReexamination()) {
            examPerformance.setReexaminationPossible(false);
        }

        if (examPerformance.getGrade() != 5.0f) {
            examPerformance.setReexaminationPossible(false);
        }

        examPerformance.setCreator(employeeService.getByUser(authenticationService.getCurrentUser()));

        examPerformanceDAO.save(examPerformance);
    }

    @Override
    public void initializeStudents(List<ExamPerformance> examPerformances) {
        examPerformances.forEach(ep -> ep.setStudent(studentDAO.findById(ep.getStudent().getId())));
    }

    @Override
    public ExamPerformanceProtocolItem reverse(long examPerformanceId) throws NotFoundException, ExamPerformanceValidationException {
        ExamPerformance examPerformance = examPerformanceDAO.findById(examPerformanceId);
        if (examPerformance == null) {
            throw new NotFoundException(ExamPerformance.class);
        }

        ExamPerformanceProtocolItem protocolItem = new ExamPerformanceProtocolItem();

        validateLastEntry(examPerformance);

        examPerformanceDAO.reverse(examPerformance);

        protocolItem.setType(REVERSAL);
        protocolItem.setOldExamPerformance(examPerformance);
        protocolItem.setEditor(employeeService.getByUser(authenticationService.getCurrentUser()));

        examPerformanceProtocolItemDAO.save(protocolItem);

        return protocolItem;
    }

    @Override
    public ExamPerformanceProtocolItem updateGrade(long examPerformanceId, float newGrade, boolean reexaminationPossible) throws NotFoundException, ExamPerformanceValidationException {
        ExamPerformance oldExamPerformance = examPerformanceDAO.findById(examPerformanceId);
        if (oldExamPerformance == null) {
            throw new NotFoundException(ExamPerformance.class);
        }

        ExamPerformanceProtocolItem protocolItem = new ExamPerformanceProtocolItem();

        validateLastEntry(oldExamPerformance);
        if (oldExamPerformance.getGrade() == newGrade && oldExamPerformance.isReexaminationPossible() == reexaminationPossible) {
            throw new ExamPerformanceValidationException(oldExamPerformance, "exception.examPerformance.unchangedGrade");
        }

        examPerformanceDAO.reverse(oldExamPerformance);

        ExamPerformance newExamPerformance = cloneExamPerformance(oldExamPerformance);
        newExamPerformance.setGrade(newGrade);
        newExamPerformance.setReexaminationPossible(reexaminationPossible);

        create(newExamPerformance);

        protocolItem.setType(EDIT);
        protocolItem.setOldExamPerformance(oldExamPerformance);
        protocolItem.setNewExamPerformance(newExamPerformance);
        protocolItem.setEditor(newExamPerformance.getCreator());

        examPerformanceProtocolItemDAO.save(protocolItem);

        return protocolItem;
    }

    @Override
    public List<ExamPerformance> listFullHistory(long courseId, long studentId) throws NotFoundException {
        return listFullHistory(AbstractEntity.withId(courseId, Course.class), AbstractEntity.withId(studentId, Student.class));
    }

    @Override
    public List<ExamPerformance> listFullHistory(Course course, Student student) {
        Objects.requireNonNull(course);
        Objects.requireNonNull(course.getId());
        Objects.requireNonNull(student);
        Objects.requireNonNull(student.getId());

        return examPerformanceDAO.findAllEntriesByCourseAndStudent(course, student);
    }

    @Override
    public ExamPerformanceProtocolItem getProtocolForPerformance(ExamPerformance examPerformance) {
        Objects.requireNonNull(examPerformance);
        Objects.requireNonNull(examPerformance.getId());

        return examPerformanceProtocolItemDAO.findByOldExamPerformance(examPerformance);
    }

    @Override
    public ExamPerformance getCurrentPerformance(Course course, Student student) throws NotFoundException {
        Objects.requireNonNull(course);
        Objects.requireNonNull(course.getId());
        Objects.requireNonNull(student);
        Objects.requireNonNull(student.getId());

        ExamPerformance examPerformance = examPerformanceDAO.findLastAttemptByCourseAndStudent(course, student);
        if (examPerformance == null) {
            throw new NotFoundException(ExamPerformance.class);
        }
        return examPerformance;
    }

    /**
     * Returns a clone of the given exam performance with all for an update relevant data.
     * (no id, new creator, not reversed, no updatedAt, no createdAt)
     *
     * @param oldExamPerformance the old exam performance
     * @return a cloned version with only update relevant data
     * @throws NotFoundException if no current user could be found
     */
    private ExamPerformance cloneExamPerformance(ExamPerformance oldExamPerformance) throws NotFoundException {
        ExamPerformance newExamPerformance = new ExamPerformance();

        newExamPerformance.setStudent(oldExamPerformance.getStudent());
        newExamPerformance.setExam(oldExamPerformance.getExam());
        newExamPerformance.setAttempt(oldExamPerformance.getAttempt());
        newExamPerformance.setReexamination(oldExamPerformance.isReexamination());

        newExamPerformance.setReversed(false);
        newExamPerformance.setCreator(employeeService.getByUser(authenticationService.getCurrentUser()));

        return newExamPerformance;
    }

    /**
     * Validates that the given exam performance is the last entry for its course and student.
     *
     * @param examPerformance the exam performance to validate as last entry
     */
    private void validateLastEntry(ExamPerformance examPerformance) throws ExamPerformanceValidationException {
        ExamPerformance lastEntry = examPerformanceDAO.findLastEntryByCourseAndStudent(examPerformance.getExam().getCourse(), examPerformance.getStudent());

        // Intentionally by Id and not via equals() to validate the persisted match
        if (!lastEntry.getId().equals(examPerformance.getId())) {
            throw new ExamPerformanceValidationException(examPerformance, "exception.examPerformance.notLastEntry");
        }
    }

    /**
     * Sets the attempt for the given exam performance subject to the last attempt.
     *
     * @param examPerformance the exam performance whose attempt will be set
     * @param lastAttempt the last attempt or null
     */
    private void setAttempt(ExamPerformance examPerformance, ExamPerformance lastAttempt) {
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

    public void setExamPerformanceProtocolItemDAO(ExamPerformanceProtocolItemDAO examPerformanceProtocolItemDAO) {
        this.examPerformanceProtocolItemDAO = examPerformanceProtocolItemDAO;
    }

}
