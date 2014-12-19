/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.presentation.model.WelcomeAdminActionModel;
import de.nak.exammgmt.presentation.model.WelcomeManagementActionModel;
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

    private WelcomeAdminActionModel welcomeAdminActionModel;
    private WelcomeManagementActionModel welcomeManagementActionModel;

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
                return LOGIN; // TODO permission for login  and here exception
        }
    }

    private void executeManagement() {

    }

    private void executeLecturer() {
        //TODO implement.
    }

    private void executeStudent() {
        //TODO check if student assigned.
    }

    private void executeAdmin() {
        welcomeAdminActionModel = new WelcomeAdminActionModel();
        welcomeAdminActionModel.setUsers(adminService.listUsers());
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public WelcomeAdminActionModel getWelcomeAdminActionModel() {
        return welcomeAdminActionModel;
    }

    public void setWelcomeAdminActionModel(WelcomeAdminActionModel welcomeAdminActionModel) {
        this.welcomeAdminActionModel = welcomeAdminActionModel;
    }

    public WelcomeManagementActionModel getWelcomeManagementActionModel() {
        return welcomeManagementActionModel;
    }

    public void setWelcomeManagementActionModel(WelcomeManagementActionModel welcomeManagementActionModel) {
        this.welcomeManagementActionModel = welcomeManagementActionModel;
    }

}
