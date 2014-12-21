/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.presentation.GradePresenter;
import de.nak.exammgmt.presentation.model.CourseActionModel;
import de.nak.exammgmt.service.CourseService;
import de.nak.exammgmt.service.Enrollment;
import de.nak.exammgmt.service.EnrollmentService;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CourseAction extends BaseAction {

    private Long courseId;

    private CourseService courseService;
    private EnrollmentService enrollmentService;

    private CourseActionModel courseActionModel = new CourseActionModel();

    @Override
    public String show() throws Exception {
        if (courseId == null) {
            return ERROR;
        }

        Course course = courseService.get(courseId);
        List<Enrollment> enrollments = enrollmentService.listByCourse(course);
        courseActionModel.setCourse(course);
        courseActionModel.setEnrollments(enrollments);
        courseActionModel.setGradeCount(enrollments.stream()
                .filter(e -> e.getGrade() != null)
                .sorted(Comparator.comparing(Enrollment::getGrade)) // TODO nicht vorhande grades trotzdem anzeigen
                .collect(Collectors.groupingBy(Enrollment::getGrade, LinkedHashMap::new, Collectors.counting()))); // TODO reeaxamination possible mit zählen, geht mit enrollment aber nicht -.-

                // TODO eigene note in liste und auswertung markieren
        courseActionModel.setAverageGrade(enrollments.stream()
                .filter(e -> e.getGrade() != null)
                .mapToDouble(Enrollment::getGrade)
                .average().orElse(0));

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

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
}
