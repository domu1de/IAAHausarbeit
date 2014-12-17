/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.service.StudentService;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StudentAction extends BaseAction {

    private Long studentId;
    private Long courseId;

    private StudentService studentService;

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
}
