/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Default implementation of the {@link StudentService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultStudentService implements StudentService {

    private StudentDAO studentDAO;
    private ExamPerformanceDAO examPerformanceDAO;

    @Override
    public Student get(long studentId) throws NotFoundException {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new NotFoundException(Student.class);
        }
        return student;
    }

    @Override
    public Student getByUser(User user) throws NotFoundException {
        // TODO not null
        Student student = studentDAO.findByUser(user);
        if (student == null) {
            throw new NotFoundException(Student.class);
        }
        return student;
    }

    @Override
    public List<Student> list() {
        return studentDAO.findAll();
    }

    @Override
    public List<ExamPerformance> listCurrentPerformancePerCourse(long studentId) throws NotFoundException {
        Student student = get(studentId);
        return listCurrentPerformancePerCourse(student);
    }

    @Override
    public List<ExamPerformance> listCurrentPerformancePerCourse(Student student) {
        // TODO not null
        return examPerformanceDAO.findLastAttemptsByStudent(student);
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setExamPerformanceDAO(ExamPerformanceDAO examPerformanceDAO) {
        this.examPerformanceDAO = examPerformanceDAO;
    }
}
