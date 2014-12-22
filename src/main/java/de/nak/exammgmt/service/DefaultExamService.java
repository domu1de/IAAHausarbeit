/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamDAO;
import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.common.mail.NotificationMail;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.ExamPerformanceValidationException;
import de.nak.exammgmt.service.exception.NotFoundException;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

/**
 * Default implementation of the {@link ExamService}.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultExamService implements ExamService {

    private ExamDAO examDAO;
    private ExamPerformanceService examPerformanceService;
    private StudentDAO studentDAO;
    private NotificationMail notificationMail;

    @Override
    public Exam get(long examId) throws NotFoundException {
        Exam exam = examDAO.findById(examId);
        if (exam == null) {
            throw new NotFoundException(Exam.class);
        }
        return exam;
    }

    @Override
    public List<Exam> list() {
        return examDAO.findAll();
    }

    @Override
    public void create(Exam exam) throws AlreadyCreatedException {
        if (exam.getId() != null) {
            throw new AlreadyCreatedException();
        }
        // TODO: fachlicher test
        examDAO.save(exam);
    }

    @Override
    public void save(Exam exam) {
        // TODO
    }

    @Override
    public void saveExamPerformances(long examId, List<ExamPerformance> examPerformances, boolean isReexamination) throws NotFoundException, ExamPerformanceValidationException {
        Exam exam = get(examId);

        List<Student> possibleAttendees = !isReexamination
                ? listPossibleAttendees(exam)
                : listPossibleReexaminationAttendees(exam);

        for (ExamPerformance examPerformance : examPerformances) {
            // Get persisted version of student
            Student student = possibleAttendees.stream()
                    .filter(s -> s.getId().equals(examPerformance.getStudent().getId()))
                    .findFirst()
                    .orElse(null);

            // Check if student is among possible attendees
            if (student == null) {
                // TODO: throw exception
                throw new RuntimeException();
            }

            examPerformance.setExam(exam);
            examPerformance.setReexamination(isReexamination);
            examPerformanceService.create(examPerformance);

            // FIXME komisch?! exception?
            if (student.getUser() != null) {
                notificationMail.generate(student.getUser().getEmail(), student.getUser().getFullName(), exam.getCourse().getTitle());
            }
        }

        // Only send notification mails if transaction was successfully committed
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                notificationMail.send();
            }
        });
    }

    @Override
    public List<Student> listPossibleAttendees(long examId) throws NotFoundException {
        Exam exam = get(examId);
        return listPossibleAttendees(exam);
    }

    @Override
    public List<Student> listPossibleAttendees(Exam exam) {
        // TODO not null
        return studentDAO.findPossibleAttendees(exam);
    }

    @Override
    public List<Student> listPossibleReexaminationAttendees(long examId) throws NotFoundException {
        Exam exam = get(examId);
        return listPossibleReexaminationAttendees(exam);
    }

    @Override
    public List<Student> listPossibleReexaminationAttendees(Exam exam) {
        // TODO not null
        return studentDAO.findPossibleReexaminationAttendees(exam);
    }

    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public void setExamPerformanceService(ExamPerformanceService examPerformanceService) {
        this.examPerformanceService = examPerformanceService;
    }

    public void setNotificationMail(NotificationMail notificationMail) {
        this.notificationMail = notificationMail;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
}
