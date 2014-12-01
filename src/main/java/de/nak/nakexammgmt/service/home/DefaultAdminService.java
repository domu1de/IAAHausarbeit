/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.home;

import de.nak.nakexammgmt.persistence.dao.user.RoleDAO;
import de.nak.nakexammgmt.persistence.entity.user.Role;
import de.nak.nakexammgmt.persistence.entity.user.User;
import de.nak.nakexammgmt.service.authentication.AuthenticationService;
import de.nak.nakexammgmt.service.exception.AlreadyActivatedException;
import de.nak.nakexammgmt.service.exception.AlreadyCreatedException;
import de.nak.nakexammgmt.service.exception.NotFoundException;
import de.nak.nakexammgmt.service.user.ActivationService;
import de.nak.nakexammgmt.service.user.PasswordResetService;
import de.nak.nakexammgmt.service.user.UserService;

import java.util.List;

/**
 * Default implementation of the AdminService.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultAdminService implements AdminService {

    private UserService userService;
    private ActivationService activationService;
    private AuthenticationService authenticationService;
    private PasswordResetService passwordResetService;
    private RoleDAO roleDAO;

    @Override
    public void createUser(User user) throws AlreadyCreatedException, AlreadyActivatedException {
        userService.create(user);
        activationService.create(user);
    }

    @Override
    public void updateUser(User user) {
        userService.update(user);
    }

    @Override
    public void deactivateUser(Long userId) throws NotFoundException {
        User user = userService.get(userId);
        passwordResetService.cancelResetProcess(user);
        activationService.cancel(user);
        authenticationService.revokeUserSessions(user);
        userService.deactivate(user);
    }

    @Override
    public void reactiveUser(Long userId) throws NotFoundException, AlreadyActivatedException {
        User user = userService.get(userId);
        activationService.create(user);
    }

    @Override
    public User getUser(Long userId) throws NotFoundException {
        return userService.get(userId);
    }

    @Override
    public List<User> listUsers() {
        return userService.list();
    }

    @Override
    public List<User> listInactiveUsers() {
        return userService.listInactiveUsers();
    }

    @Override
    public List<User> listActiveUsers() {
        return userService.listActiveUsers();
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.findAllWithoutGuest();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setActivationService(ActivationService activationService) {
        this.activationService = activationService;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
}
