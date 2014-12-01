/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.model;

import de.nak.nakexammgmt.persistence.entity.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to encapsulate admin related entities against the presentation layer using the concept of form beans.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class WelcomeAdminModel {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
