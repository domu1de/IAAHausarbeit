/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.presentation.GradePresenter;
import de.nak.exammgmt.presentation.model.StudentActionModel;
import de.nak.exammgmt.service.ExamPerformanceService;
import de.nak.exammgmt.service.StudentService;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StudentAction extends BaseAction {

    private Long studentId;
    private Long courseId;

    private StudentService studentService;
    private ExamPerformanceService examPerformanceService;

    private StudentActionModel studentActionModel = new StudentActionModel();

    public String show() throws NotFoundException {
        if (studentId == null) {
            return ERROR; //FIXME
        }

        studentActionModel.setLastAttempts(examPerformanceService.listLastAttempts(studentId));

        return SHOW;
    }

    public String personalGrades() {
        return "";
    }

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
}
