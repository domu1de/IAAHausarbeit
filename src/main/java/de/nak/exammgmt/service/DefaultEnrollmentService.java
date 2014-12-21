/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.persistence.entity.Student;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Default implementation of {@link EnrollmentService}.
 *
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
            Enrollment enrollment = new Enrollment(student, course);

            for (int index = 0; index < lastAttempts.size(); index++) {
                ExamPerformance examPerformance = lastAttempts.get(index);
                if (examPerformance.getExam().getCourse().equals(course)) {
                    applyExamPerformance(enrollment, examPerformance);
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
            Enrollment enrollment = new Enrollment(student, course);

            for (int index = 0; index < examPerformances.size(); index++) {
                ExamPerformance examPerformance = examPerformances.get(index);
                if (examPerformance.getStudent().equals(student)) {
                    applyExamPerformance(enrollment, examPerformance);
                    examPerformances.remove(index);
                    break;
                }
            }

            enrollments.add(enrollment);
        }

        return enrollments;
    }

    @Override
    public Map<Course, Map<Student, Enrollment>> listByManiple(long manipleId) throws NotFoundException {
        Maniple maniple = manipleService.get(manipleId); // FIXME: nur wrappen? Query unnötig. Frage stellt sich für alle service aufrufe
        return listByManiple(maniple);
    }

    @Override
    public Map<Course, Map<Student, Enrollment>> listByManiple(Maniple maniple) {
        Comparator<Enrollment> studentComparator = Comparator.comparing((Enrollment e) -> e.getStudent().getLastName())
                .thenComparing((Enrollment e) -> e.getStudent().getFirstName());
        Comparator<Course> courseComparator = Comparator.comparing(Course::getTitle);

        return manipleService.listCourses(maniple).stream()
                .sorted(courseComparator)
                .collect(toMap(c -> c, c -> listByCourse(c).stream()
                        .sorted(studentComparator)
                        .collect(toMap(Enrollment::getStudent, e -> e))));
    }

    private float getGrade(ExamPerformance examPerformance) {
        if (examPerformance.isReexamination()) {
            return (examPerformance.getGrade() <= 3) ? 4 : 5;
        } else {
            return examPerformance.getGrade();
        }
    }

    private void applyExamPerformance(Enrollment enrollment, ExamPerformance examPerformanceToApply) {
        enrollment.setGrade(getGrade(examPerformanceToApply));
        enrollment.setAttempt(examPerformanceToApply.getAttempt());
        enrollment.setReexaminationPossible(examPerformanceToApply.isReexaminationPossible());
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
