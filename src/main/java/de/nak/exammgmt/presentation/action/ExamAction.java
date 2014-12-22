/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Course;
import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.ExamActionModel;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.ManipleService;

import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toMap;

/**
 * RESTful action to handle exams.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class ExamAction extends BaseAction {

    private ExamActionModel examActionModel = new ExamActionModel();

    private Long examId;

    private ExamService examService;
    private ManipleService manipleService;

    @Override
    public String editNew() throws Exception {
        for (Maniple maniple : manipleService.list()) {
            List<Course> courses = manipleService.listCourses(maniple);
            examActionModel.getManiples().add(maniple.toString());
            examActionModel.putCourses(maniple.toString(),
                    courses.stream().collect(toMap(Course::getId, Course::toString)));

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
            return ERROR;
        }

        exam.setLecturers(new HashSet<>(examActionModel.getLecturers()));
        examService.create(examActionModel.getExam());

        addActionMessage(getText("txt.examSuccessfullyCreated"));
        return REDIRECT_WELCOME;
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
