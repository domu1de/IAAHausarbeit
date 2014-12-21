/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.presentation.GradePresenter;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.StudentActionModel;
import de.nak.exammgmt.service.EnrollmentService;
import de.nak.exammgmt.service.ExamPerformanceService;
import de.nak.exammgmt.service.StudentService;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class StudentAction extends BaseAction {

    private static final String SHOW_GRADES = "show_grades";
    private static final String SHOW_COURSE_GRADE = "show_course_grade";

    private Long studentId;
    private Long course;

    private StudentService studentService;
    private ExamPerformanceService examPerformanceService;
    private EnrollmentService enrollmentService;

    private StudentActionModel studentActionModel = new StudentActionModel();

    @Override
    public String show() throws Exception {
        if (studentId == null) {
            return ERROR; //FIXME
        }

        studentActionModel.setStudent(studentService.get(studentId));

        if (course != null) {
            studentActionModel.setEnrollment(enrollmentService.getByStudentAndCourse(studentId, course));

            for (ExamPerformance ep : examPerformanceService.listFullHistory(course, studentId)) {
                studentActionModel.getFullHistory().put(ep, examPerformanceService.getProtocolForPerformance(ep));
            }

            return SHOW_COURSE_GRADE;
        }

        studentActionModel.setEnrollments(enrollmentService.listByStudent(studentId));

        return SHOW_GRADES;
    }

    public String personalGrades() throws Exception {
        studentId = studentService.getByUser(getCurrentUser()).getId(); // FIXME duplicate
        return show();
    }

    // TODO: direct call in thyemleaf?!
    public String toCssClass(float grade) {
        return GradePresenter.toCssClass(grade);
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public void setExamPerformanceService(ExamPerformanceService examPerformanceService) {
        this.examPerformanceService = examPerformanceService;
    }

    public StudentActionModel getStudentActionModel() {
        return studentActionModel;
    }

    public void setStudentActionModel(StudentActionModel studentActionModel) {
        this.studentActionModel = studentActionModel;
    }

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

}
