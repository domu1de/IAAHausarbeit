/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.presentation.model.UserActionModel;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.service.home.AdminService;

/**
 * RESTful action to manage User resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class UsersAction extends BaseAction {

    private AdminService adminService;

    private Long userId;
    private UserActionModel userActionModel;

    public String index() {
        userActionModel = new UserActionModel();
        userActionModel.setUsers(adminService.listUsers());
        return INDEX;
    }

    public String show() throws NotFoundException {
        userActionModel = new UserActionModel();
        userActionModel.setUser(adminService.getUser(userId));
        return SHOW;
    }

    public String editNew() {
        userActionModel = new UserActionModel();
        userActionModel.setRoles(adminService.getRoles());
        return NEW;
    }

    public String edit() throws NotFoundException {
        userActionModel = new UserActionModel();
        userActionModel.setUser(adminService.getUser(userId));
        return EDIT;
    }

    public String update() throws Exception {
        if (userActionModel == null || userActionModel.getUser() == null) {
            addActionError("no user data submitted");
            return ERROR;
        }
        adminService.updateUser(userActionModel.getUser());
        return SHOW;
    }

    public String create() throws Exception {
        if (userActionModel == null || userActionModel.getUser() == null) {
            addActionError("no user data submitted");
            return ERROR;
        }
        adminService.createUser(userActionModel.getUser());
        userId = userActionModel.getUser().getId();
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

    public UserActionModel getUserActionModel() {
        return userActionModel;
    }

    public void setUserActionModel(UserActionModel userActionModel) {
        this.userActionModel = userActionModel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
