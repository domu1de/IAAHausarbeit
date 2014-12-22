/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.ExamPerformanceActionModel;
import de.nak.exammgmt.service.ExamPerformanceService;
import de.nak.exammgmt.service.common.UrlProvider;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class ExamPerformanceAction extends BaseAction {

    private Long examPerformanceId;

    private ExamPerformanceActionModel examPerformanceActionModel;

    private ExamPerformanceService examPerformanceService;
    private UrlProvider urlProvider;

    @Override
    public String update() throws Exception {
        return super.update();
    }

    @Override
    public String remove() throws Exception {
        if (examPerformanceId == null) {
            return ERROR; //FIXME
        }

        ExamPerformanceProtocolItem protocolItem = examPerformanceService.reverse(examPerformanceId);

        returnTo = urlProvider.urlForStudentAndCourse(
                protocolItem.getOldExamPerformance().getExam().getCourse().getId(),
                protocolItem.getOldExamPerformance().getStudent().getId());

        return REMOVE;
    }

    public Long getExamPerformanceId() {
        return examPerformanceId;
    }

    public void setExamPerformanceId(Long examPerformanceId) {
        this.examPerformanceId = examPerformanceId;
    }

    public void setUrlProvider(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
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
