/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.ManipleActionModel;
import de.nak.exammgmt.service.EnrollmentService;
import de.nak.exammgmt.service.ManipleService;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class ManipleAction extends BatchAction {

    private Long manipleId;

    private ManipleActionModel manipleActionModel = new ManipleActionModel();

    private EnrollmentService enrollmentService;
    private ManipleService manipleService;

    @Override
    public String index() throws Exception {
        manipleActionModel.setManiples(manipleService.list());
        return INDEX;
    }

    @Override
    public String show() throws Exception {
        if (manipleId == null) {
            return ERROR; // FIXME
        }

        Maniple maniple = manipleService.get(manipleId);
        manipleActionModel.setManiple(maniple);
        manipleActionModel.setManipleCourses(manipleService.listCourses(maniple));
        manipleActionModel.setManipleEnrollments(enrollmentService.listByManiple(maniple));

        return SHOW;
    }

    public Long getManipleId() {
        return manipleId;
    }

    public void setManipleId(Long manipleId) {
        this.manipleId = manipleId;
    }

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public void setManipleService(ManipleService manipleService) {
        this.manipleService = manipleService;
    }

    public ManipleActionModel getManipleActionModel() {
        return manipleActionModel;
    }

    public void setManipleActionModel(ManipleActionModel manipleActionModel) {
        this.manipleActionModel = manipleActionModel;
    }
}
