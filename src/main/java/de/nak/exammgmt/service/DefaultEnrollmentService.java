/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultEnrollmentService implements EnrollmentService {

    private StudentService studentService;
    private CourseService courseService;
    private ManipleService manipleService;

    @Override
    public Enrollment getByStudentAndCourse(long studentId, long courseId) {
        // TODO
        return null;
    }

    @Override
    public List<Enrollment> listByStudent(long studentId) throws NotFoundException {
        Student student = studentService.get(studentId);
        return listByStudent(student);
    }

    @Override
    public List<Enrollment> listByStudent(Student student) {
        List<Course> courses = manipleService.listCourses(student.getManiple());
        List<ExamPerformance> lastAttempts = studentService.listCurrentPerformancePerCourse(student);

        List<Enrollment> enrollments = new ArrayList<>(courses.size());
        for (Course course : courses) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);

            // TODO: equals
            for (int index = 0; index < lastAttempts.size(); index++) {
                ExamPerformance examPerformance = lastAttempts.get(index);
                if (examPerformance.getExam().getCourse().getId().equals(course.getId())) {
                    enrollment.setGrade(getGrade(examPerformance));
                    enrollment.setAttempt(examPerformance.getAttempt());
                    lastAttempts.remove(index);
                    break;
                }
            }

            enrollments.add(enrollment);
        }

        return enrollments;
    }

    @Override
    public List<Enrollment> listByCourse(long courseId) throws NotFoundException {
        Course course = courseService.get(courseId);
        return listByCourse(course);
    }

    @Override
    public List<Enrollment> listByCourse(Course course) {
        List<Student> students = manipleService.listStudents(course.getManiple());
        List<ExamPerformance> examPerformances = courseService.listCurrentPerformancePerStudent(course);

        List<Enrollment> enrollments = new ArrayList<>(students.size());
        for (Student student : students) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);

            // TODO: equals
            for (int index = 0; index < examPerformances.size(); index++) {
                ExamPerformance examPerformance = examPerformances.get(index);
                if (examPerformance.getStudent().getId().equals(student.getId())) {
                    enrollment.setGrade(getGrade(examPerformance));
                    enrollment.setAttempt(examPerformance.getAttempt());
                    examPerformances.remove(index);
                    break;
                }
            }

            enrollments.add(enrollment);
        }

        return enrollments;
    }

    private float getGrade(ExamPerformance examPerformance) {
        if (examPerformance.isReexamination()) {
            return (examPerformance.getGrade() <= 3) ? 4 : 5;
        } else {
            return examPerformance.getGrade();
        }
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setManipleService(ManipleService manipleService) {
        this.manipleService = manipleService;
    }
}
