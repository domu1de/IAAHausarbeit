/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import de.nak.nakexammgmt.presentation.model.WelcomeAdminModel;
import de.nak.nakexammgmt.service.home.AdminService;

/**
 * Action that is mapped to the root URL of the application and provides different landing pages for the three roles
 * of the application.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class WelcomeAction extends BaseAction {

    private static final String ADMIN = "admin";
    private static final String STUDENT = "student";
    private static final String TEACHER = "teacher";
    private static final String MANAGEMENT = "management";

    private AdminService adminService;

    private WelcomeAdminModel welcomeAdminModel;

    @Override
    public String execute() throws Exception {
        switch (getCurrentUser().getRole().getName()) {
            case ADMIN:
                executeAdmin();
                return ADMIN;
            case STUDENT:
                executeStudent();
                return STUDENT;
            case TEACHER:
                executeTeacher();
                return TEACHER;
            case MANAGEMENT:
                executeManagement();
                return MANAGEMENT;
            default:
                return LOGIN;
        }
    }

    private void executeManagement() {
        // TODO implement.
    }

    private void executeTeacher() {
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

    public WelcomeAdminModel getWelcomeAdminModel() {
        return welcomeAdminModel;
    }

    public void setWelcomeAdminModel(WelcomeAdminModel welcomeAdminModel) {
        this.welcomeAdminModel = welcomeAdminModel;
    }
}
