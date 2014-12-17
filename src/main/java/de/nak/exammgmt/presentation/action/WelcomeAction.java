/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.presentation.model.WelcomeAdminModel;
import de.nak.exammgmt.presentation.model.WelcomeManagementModel;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.home.AdminService;

/**
 * Action that is mapped to the root URL of the application and provides different landing pages for the three roles
 * of the application.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class WelcomeAction extends BaseAction {

    private static final String ADMIN = "admin";
    private static final String STUDENT = "student";
    private static final String LECTURER = "lecturer";
    private static final String MANAGEMENT = "management";

    private AdminService adminService;
    private ExamService examService;

    private WelcomeAdminModel welcomeAdminModel;
    private WelcomeManagementModel welcomeManagementModel;

    @Override
    public String execute() throws Exception {
        switch (getCurrentUser().getRole().getName()) {
            case ADMIN:
                executeAdmin();
                return ADMIN;
            case STUDENT:
                executeStudent();
                return STUDENT;
            case LECTURER:
                executeLecturer();
                return LECTURER;
            case MANAGEMENT:
                executeManagement();
                return MANAGEMENT;
            default:
                return LOGIN;
        }
    }

    private void executeManagement() {

    }

    private void executeLecturer() {
        //TODO implement.
    }

    private void executeStudent() {
        //TODO implement.
    }

    private void executeAdmin() {
        welcomeAdminModel = new WelcomeAdminModel();
        welcomeAdminModel.setUsers(adminService.listUsers());
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public WelcomeAdminModel getWelcomeAdminModel() {
        return welcomeAdminModel;
    }

    public void setWelcomeAdminModel(WelcomeAdminModel welcomeAdminModel) {
        this.welcomeAdminModel = welcomeAdminModel;
    }

    public WelcomeManagementModel getWelcomeManagementModel() {
        return welcomeManagementModel;
    }

    public void setWelcomeManagementModel(WelcomeManagementModel welcomeManagementModel) {
        this.welcomeManagementModel = welcomeManagementModel;
    }

}
