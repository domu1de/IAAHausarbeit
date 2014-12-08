/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import de.nak.exammgmt.persistence.entity.user.Role;
import de.nak.exammgmt.persistence.entity.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to encapsulate User entities against the presentation layer using the concept of form beans.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class UserModel {

    private User user;
    private List<User> users = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
