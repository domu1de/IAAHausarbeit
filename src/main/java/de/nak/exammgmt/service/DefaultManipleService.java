/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.CourseDAO;
import de.nak.exammgmt.persistence.dao.ManipleDAO;
import de.nak.exammgmt.persistence.dao.StudentDAO;
import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

/**
 * Default implementation of the {@link ManipleService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultManipleService implements ManipleService {

    private ManipleDAO manipleDAO;
    private CourseDAO courseDAO;
    private StudentDAO studentDAO;

    @Override
    public Maniple get(long manipleId) throws NotFoundException {
        Maniple maniple = manipleDAO.findById(manipleId);
        if (maniple == null) {
            throw new NotFoundException(Maniple.class);
        }
        return maniple;
    }

    @Override
    public List<Maniple> list() {
        return manipleDAO.findAll();
    }

    @Override
    public List<Course> listCourses(long manipleId) throws NotFoundException {
        Maniple maniple = get(manipleId);
        return listCourses(maniple);
    }

    @Override
    public List<Course> listCourses(Maniple maniple) {
        Objects.requireNonNull(maniple);
        Objects.requireNonNull(maniple.getId());

        return courseDAO.findByManiple(maniple);
    }

    @Override
    public List<Student> listStudents(long manipleId) throws NotFoundException {
        Maniple maniple = get(manipleId);
        return listStudents(maniple);
    }

    @Override
    public List<Student> listStudents(Maniple maniple) {
        Objects.requireNonNull(maniple);
        Objects.requireNonNull(maniple.getId());

        return studentDAO.findByManiple(maniple);
    }

    public void setManipleDAO(ManipleDAO manipleDAO) {
        this.manipleDAO = manipleDAO;
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

}
