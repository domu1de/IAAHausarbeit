/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.presentation.model.BatchActionModel;
import de.nak.exammgmt.service.ExamPerformanceService;
import de.nak.exammgmt.service.ExamService;

import java.util.Collections;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class BatchAction extends BaseAction {

    private Long examId;
    private boolean reexamination = false;

    private ExamService examService;
    private ExamPerformanceService examPerformanceService;

    private BatchActionModel batchActionModel = new BatchActionModel();

    @Override
    public String show() throws Exception {
        if (examId == null) {
            return NOT_FOUND;
        }

        Exam exam = examService.get(examId);

        batchActionModel.setPossibleAttendees(reexamination
                ? examService.listPossibleReexaminationAttendees(exam)
                : examService.listPossibleAttendees(exam));

        return NEW;
    }

    @Override
    public String create() throws Exception {
        if (examId == null) {
            return NOT_FOUND;
        }

        filterExamPerformances();

        if (batchActionModel.getExamPerformances().isEmpty()) {
            // TODO: error
        }

        examService.saveExamPerformances(examId, batchActionModel.getExamPerformances(), reexamination);
        examPerformanceService.initializeStudents(batchActionModel.getExamPerformances());
        batchActionModel.setCreator(batchActionModel.getExamPerformances().get(0).getCreator());
        batchActionModel.setExam(batchActionModel.getExamPerformances().get(0).getExam()); // FIXME

        return CREATE;
    }

    private void filterExamPerformances() {
        batchActionModel.getExamPerformances().removeAll(Collections.singleton(null)); // TODO: oder streaming api?
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

    public void setExamPerformanceService(ExamPerformanceService examPerformanceService) {
        this.examPerformanceService = examPerformanceService;
    }
}
