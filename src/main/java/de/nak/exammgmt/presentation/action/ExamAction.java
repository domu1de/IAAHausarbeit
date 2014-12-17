/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.presentation.model.ExamModel;
import de.nak.exammgmt.service.CourseService;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.ManipleService;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;

import java.util.HashSet;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ExamAction extends BaseAction {

    private ExamModel examModel = new ExamModel();

    private Long examId;

    private ExamService examService;
    private CourseService courseService;
    private ManipleService manipleService;


    public String editNew() {
        examModel.setManiples(manipleService.list());
        for (Maniple maniple : examModel.getManiples()) {
            examModel.putManipleCourse(maniple.getId(), courseService.list(maniple));
        }
        return NEW;
    }

    public String create() {
        Exam exam = examModel.getExam();

        if (exam == null) {
            addActionError("bg"); // FIXME
            return ERROR;
        }

        exam.setLecturers(new HashSet<>(examModel.getLecturers()));

        try {
            examService.create(examModel.getExam());
        } catch (AlreadyCreatedException e) {
            addActionError("bla2");
            return ERROR; // FIXME
        }

        return SHOW;
    }

    public ExamModel getExamModel() {
        return examModel;
    }

    public void setExamModel(ExamModel examModel) {
        this.examModel = examModel;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public void setManipleService(ManipleService manipleService) {
        this.manipleService = manipleService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
