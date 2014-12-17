/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Default implementation of the {@link StudentService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultStudentService implements StudentService {

    private StudentDAO studentDAO;

    @Override
    public Student get(long studentId) throws NotFoundException {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new NotFoundException(Student.class);
        }
        return student;
    }

    @Override
    public List<Student> listPossibleAttendees(Exam exam) {
        // TODO: check exam
        return studentDAO.findPossibleAttendees(exam);
    }

    @Override
    public List<Student> listPossibleReexaminationAttendees(Exam exam) {
        return studentDAO.findPossibleReexaminationAttendees(exam);
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
}
