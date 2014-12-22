/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.user.Permission;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.ExamPerformanceActionModel;
import de.nak.exammgmt.service.ExamPerformanceService;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class ExamPerformanceAction extends BaseAction {

    private Long examPerformanceId;

    private ExamPerformanceActionModel examPerformanceActionModel;

    private ExamPerformanceService examPerformanceService;

    @Override
    @Protected(Permission.EDIT_GRADE)
    public String update() throws Exception {
        if (examPerformanceId == null) {
            return NOT_FOUND;
        }

        ExamPerformance examPerformance = examPerformanceActionModel.getExamPerformance();
        examPerformanceService.updateGrade(examPerformanceId, examPerformance.getGrade(), examPerformance.isReexaminationPossible());

        examPerformanceId = null; // Avoid examPerformanceId in query string
        addActionMessage(getText("txt.gradeChangeSuccessful"));
        return UPDATE;
    }

    @Override
    @Protected(Permission.REVERSE_GRADE)
    public String remove() throws Exception {
        if (examPerformanceId == null) {
            return ERROR; //FIXME
        }

        examPerformanceService.reverse(examPerformanceId);

        examPerformanceId = null; // Avoid examPerformanceId in query string
        addActionMessage(getText("txt.gradeReversalSuccessful"));
        return REMOVE;
    }

    public Long getExamPerformanceId() {
        return examPerformanceId;
    }

    public void setExamPerformanceId(Long examPerformanceId) {
        this.examPerformanceId = examPerformanceId;
    }

    public void setExamPerformanceService(ExamPerformanceService examPerformanceService) {
        this.examPerformanceService = examPerformanceService;
    }

    public ExamPerformanceActionModel getExamPerformanceActionModel() {
        return examPerformanceActionModel;
    }

    public void setExamPerformanceActionModel(ExamPerformanceActionModel examPerformanceActionModel) {
        this.examPerformanceActionModel = examPerformanceActionModel;
    }

}
