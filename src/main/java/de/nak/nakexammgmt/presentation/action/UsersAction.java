/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import de.nak.nakexammgmt.presentation.model.UserModel;
import de.nak.nakexammgmt.service.exception.NotFoundException;
import de.nak.nakexammgmt.service.home.AdminService;

/**
 * RESTful action to manage User resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class UsersAction extends BaseAction {

    private AdminService adminService;

    private Long userId;
    private UserModel userModel;

    public String index() {
        userModel = new UserModel();
        userModel.setUsers(adminService.listUsers());
        return INDEX;
    }

    public String show() throws NotFoundException {
        userModel = new UserModel();
        userModel.setUser(adminService.getUser(userId));
        return SHOW;
    }

    public String editNew() {
        userModel = new UserModel();
        userModel.setRoles(adminService.getRoles());
        return NEW;
    }

    public String edit() throws NotFoundException {
        userModel = new UserModel();
        userModel.setUser(adminService.getUser(userId));
        return EDIT;
    }

    public String update() throws Exception {
        if (userModel == null || userModel.getUser() == null) {
            addActionError("no user data submitted");
            return ERROR;
        }
        adminService.updateUser(userModel.getUser());
        return SHOW;
    }

    public String create() throws Exception {
        if (userModel == null || userModel.getUser() == null) {
            addActionError("no user data submitted");
            return ERROR;
        }
        adminService.createUser(userModel.getUser());
        userId = userModel.getUser().getId();
        return CREATE;
    }

    public String deactivate() throws Exception {
        if (userId.equals(getCurrentUser().getId())) {
            addActionError("you cannot deactivate yourself");
            return ERROR;
        }
        adminService.deactivateUser(userId);
        return INDEX;
    }

    public String reactivate() throws Exception {
        if (userId.equals(getCurrentUser().getId())) {
            addActionError("you cannot reactivate yourself");
            return ERROR;
        }
        adminService.deactivateUser(userId);
        return INDEX;
    }

    public String profile() throws NotFoundException {
        userId = getCurrentUser().getId();
        return show();
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
