/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation;

import de.nak.exammgmt.presentation.action.BaseAction;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class CourseAction extends BaseAction {

    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
