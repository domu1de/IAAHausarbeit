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

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CourseAction extends BaseAction {

    private static final float[] GRADES_IN_VIEW = {1.0f, 1.3f, 1.7f, 2.0f, 2.3f, 2.7f, 3.0f, 3.3f, 3.7f, 4.0f, 5.0f, 6.0f};

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
        courseActionModel.setGradeCount(putMissingGrades(enrollments.stream()
                .filter(e -> e.getGrade() != null)
                .collect(Collectors.groupingBy(
                        e -> e.isReexaminationPossible() ? 6.0F : e.getGrade(),
                        TreeMap::new,
                        Collectors.counting()))));

        // TODO eigene note in liste und auswertung markieren
        courseActionModel.setAverageGrade(enrollments.stream()
                .filter(e -> e.getGrade() != null)
                .mapToDouble(Enrollment::getGrade)
                .average().orElse(0));

        return SHOW;
    }

    private SortedMap<Float, Long> putMissingGrades(SortedMap<Float, Long> map) {
        for (float grade : GRADES_IN_VIEW) {
           map.putIfAbsent(grade, 0L);
        }
        return map;
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
