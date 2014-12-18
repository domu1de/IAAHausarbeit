/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.presentation.GradePresenter;
import de.nak.exammgmt.presentation.model.CourseActionModel;
import de.nak.exammgmt.service.CourseService;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CourseAction extends BaseAction {

    private Long courseId;

    private CourseService courseService;

    private CourseActionModel courseActionModel = new CourseActionModel();

    public String show() throws NotFoundException {
        if (courseId == null) {
            return ERROR;
        }

        Course course = courseService.get(courseId);
        courseActionModel.setCourse(course);
        courseActionModel.setCurrentPerformances(courseService.listCurrentPerformancePerStudent(course));

        return SHOW;
    }

    public String toCssClass(float grade) {
        return GradePresenter.toCssClass(grade);
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseActionModel getCourseActionModel() {
        return courseActionModel;
    }
}
