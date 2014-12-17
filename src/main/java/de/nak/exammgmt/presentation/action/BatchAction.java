/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.presentation.model.BatchActionModel;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.StudentService;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class BatchAction extends BaseAction {

    private Long examId;

    private ExamService examService;
    private StudentService studentService;

    private BatchActionModel batchActionModel = new BatchActionModel();

    public String show() throws NotFoundException {
        if (examId == null) {
            return NOT_FOUND;
        }

        Exam exam = examService.get(examId);

        batchActionModel.setPossibleAttendees(studentService.listPossibleAttendees(exam));

        return NEW;
    }

    public String create() throws NotFoundException {
        if (examId == null) {
            return NOT_FOUND;
        }

        if (batchActionModel.getExamPerformances().isEmpty()) {
            // TODO: error
        }

        examService.saveExamPerformances(examId, batchActionModel.getExamPerformances(), false);

        return CREATE;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public BatchActionModel getBatchActionModel() {
        return batchActionModel;
    }

    public void setBatchActionModel(BatchActionModel batchActionModel) {
        this.batchActionModel = batchActionModel;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}