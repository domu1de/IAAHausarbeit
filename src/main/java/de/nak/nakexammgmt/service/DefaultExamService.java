/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service;

import de.nak.nakexammgmt.persistence.dao.ExamDAO;
import de.nak.nakexammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.nakexammgmt.persistence.dao.StudentDAO;
import de.nak.nakexammgmt.persistence.entity.Exam;
import de.nak.nakexammgmt.persistence.entity.Student;
import de.nak.nakexammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultExamService implements ExamService {

    private ExamDAO examDAO;
    private ExamPerformanceDAO examPerformanceDAO;
    private StudentDAO studentDAO;

    @Override
    public void create(Exam exam) {

    }

    @Override
    public void save(Exam exam) {

    }

    @Override
    public List<Student> listPossibleAttendees(long examID) throws NotFoundException {
        Exam exam = examDAO.findById(examID);
        if (exam == null) {
            throw new NotFoundException(Exam.class);
        }
        return studentDAO.findPossibleAttendees(exam);
    }

    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }
}
