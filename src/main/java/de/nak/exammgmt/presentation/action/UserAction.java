/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.user.Permission;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.UserActionModel;
import de.nak.exammgmt.service.home.AdminService;

/**
 * RESTful action to manage User resources.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class UserAction extends BaseAction {

    private AdminService adminService;

    private Long userId;
    private UserActionModel userActionModel;

    @Override
    public String index() {
        userActionModel = new UserActionModel();
        userActionModel.setUsers(adminService.listUsers());

        return INDEX;
    }

    @Override
    public String show() throws Exception {
        userActionModel = new UserActionModel();
        userActionModel.setUser(adminService.getUser(userId));

        return SHOW;
    }

    @Override
    @Protected(Permission.CREATE_USER)
    public String editNew() {
        userActionModel = new UserActionModel();
        userActionModel.setRoles(adminService.getRoles());

        return NEW;
    }

    @Override
    @Protected(Permission.EDIT_USER)
    public String edit() throws Exception {
        userActionModel = new UserActionModel();
        userActionModel.setUser(adminService.getUser(userId));

        return EDIT;
    }

    @Override
    @Protected(Permission.EDIT_USER)
    public String update() throws Exception {
        if (userActionModel == null || userActionModel.getUser() == null) {
            return ERROR;
        }
        adminService.updateUser(userActionModel.getUser());

        return SHOW;
    }

    @Override
    @Protected(Permission.CREATE_USER)
    public String create() throws Exception {
        if (userActionModel == null || userActionModel.getUser() == null) {
            return ERROR;
        }

        adminService.createUser(userActionModel.getUser());
        userId = userActionModel.getUser().getId();
        return CREATE;
    }

    @Protected(Permission.EDIT_USER)
    public String deactivate() throws Exception {
        if (userId.equals(getCurrentUser().getId())) {
            return ERROR;
        }

        adminService.deactivateUser(userId);
        return INDEX;
    }

    @Protected(Permission.EDIT_USER)
    public String reactivate() throws Exception {
        if (userId.equals(getCurrentUser().getId())) {
            return ERROR;
        }

        adminService.deactivateUser(userId);
        return INDEX;
    }

    public String profile() throws Exception {
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
