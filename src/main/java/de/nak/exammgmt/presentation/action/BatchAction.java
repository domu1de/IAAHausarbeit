/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.presentation.model.BatchActionModel;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class BatchAction extends BaseAction {

    private Long examId;
    private boolean reexamination = false;

    private ExamService examService;

    private BatchActionModel batchActionModel = new BatchActionModel();

    public String show() throws NotFoundException {
        if (examId == null) {
            return NOT_FOUND;
        }

        Exam exam = examService.get(examId);

        batchActionModel.setPossibleAttendees(reexamination
                ? examService.listPossibleReexaminationAttendees(exam)
                : examService.listPossibleAttendees(exam));

        return NEW;
    }

    public String create() throws Exception {
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

    public boolean isReexamination() {
        return reexamination;
    }

    public void setReexamination(boolean reexamination) {
        this.reexamination = reexamination;
    }
}
