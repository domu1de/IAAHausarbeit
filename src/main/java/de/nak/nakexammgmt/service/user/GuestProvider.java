/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.user;

import de.nak.nakexammgmt.persistence.dao.user.RoleDAO;
import de.nak.nakexammgmt.persistence.entity.user.User;

/**
 * Class that provides a singleton guest user.
 * This guest user is not a persisted entity but holds a persisted quest-role to allow access to views like "copyright"
 * or "about" in the future.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class GuestProvider {

    private static final String FULL_NAME = "Guest";
    private static final String USERNAME = "guest";
    private static final String ROLE_NAME = "guest";

    private User guest;
    private RoleDAO roleDAO;

    /**
     * Gives a singleton User instance which is marked as guest and uses the persisted guest-role.
     *
     * @return the guest user.
     */
    public User getCurrentInstance() {
        if (guest != null) {
            return guest;
        }
        guest = new User() {
            @Override
            public boolean isLoggedIn() {
                return false;
            }
        };
        guest.setLastName(FULL_NAME);
        guest.setUsername(USERNAME);
        guest.setRole(roleDAO.findByName(ROLE_NAME));
        return guest;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}
