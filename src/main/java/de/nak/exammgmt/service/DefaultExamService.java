/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamDAO;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultExamService implements ExamService {

    private ExamDAO examDAO;
    private ExamPerformanceService examPerformanceService;
    private StudentService studentService;

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
    public List<Student> listPossibleAttendees(long examId) throws NotFoundException {
        Exam exam = examDAO.findById(examId);
        if (exam == null) {
            throw new NotFoundException(Exam.class);
        }
        return studentService.listPossibleAttendees(exam);
    }

    @Override
    public Exam get(long examId) throws NotFoundException {
        Exam exam = examDAO.findById(examId);
        if (exam == null) {
            throw new NotFoundException(Exam.class);
        }
        return exam;
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
}
