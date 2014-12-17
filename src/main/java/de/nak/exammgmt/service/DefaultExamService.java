/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamDAO;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.common.mail.NotificationMail;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
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
    private StudentService studentService;
    private NotificationMail notificationMail;

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

    }

    @Override
    public Exam get(long examId) throws NotFoundException {
        Exam exam = examDAO.findById(examId);
        if (exam == null) {
            throw new NotFoundException(Exam.class);
        }
        return exam;
    }

    @Override
    public void saveExamPerformances(long examId, List<ExamPerformance> examPerformances, boolean isReexamination) throws NotFoundException {
        Exam exam = get(examId);

        List<Student> possibleAttendees = !isReexamination
                ? studentService.listPossibleAttendees(exam)
                : studentService.listPossibleReexaminationAttendees(exam);

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
        return null;
    }

    @Override
    public List<Student> listPossibleReexaminationAttendees(long examId) throws NotFoundException {
        return null;
    }

    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public void setExamPerformanceService(ExamPerformanceService examPerformanceService) {
        this.examPerformanceService = examPerformanceService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setNotificationMail(NotificationMail notificationMail) {
        this.notificationMail = notificationMail;
    }
}
