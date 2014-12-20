/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.presentation.model.ExamActionModel;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.ManipleService;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;

import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toMap;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ExamAction extends BaseAction {

    private ExamActionModel examActionModel = new ExamActionModel();

    private Long examId;

    private ExamService examService;
    private ManipleService manipleService;

    @Override
    public String editNew() throws Exception {
        for (Maniple maniple : manipleService.list()) {
            List<Course> courses = manipleService.listCourses(maniple);
            examActionModel.getManiples().add(maniple.getAbbreviation());
            examActionModel.putCourses(maniple.getAbbreviation(),
                    courses.stream().collect(toMap(Course::getId, Course::getTitle)));

            for (Course course : courses) {
                examActionModel.putLecturers(course.getId(), course.getLecturers().stream()
                                .collect(toMap(Employee::getId, Employee::getName)));
            }
        }
        return NEW;
    }

    @Override
    public String create() throws Exception {
        Exam exam = examActionModel.getExam();

        if (exam == null) {
            addActionError("bg"); // FIXME
            return ERROR;
        }

        exam.setLecturers(new HashSet<>(examActionModel.getLecturers()));

        try {
            examService.create(examActionModel.getExam());
        } catch (AlreadyCreatedException e) {
            addActionError("bla2");
            return ERROR; // FIXME
        }

        return SHOW;
    }

    public ExamActionModel getExamActionModel() {
        return examActionModel;
    }

    public void setExamActionModel(ExamActionModel examActionModel) {
        this.examActionModel = examActionModel;
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

}
