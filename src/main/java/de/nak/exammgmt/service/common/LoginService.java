/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;

import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * Contract for a service to manage the logins of the system.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface LoginService {

    /**
     * TODO comment
     * Validates the attempt of a user to login. Creates a new UserSession if successful.
     *
     * @param usernameOrEmail the username or email address the user tries to login with.
     * @param password        the password entered by the user.
     * @param rememberMe      flag to indicate if the user wishes the system to remember his session.
     * @return whether or not the login was valid.
     */
    boolean login(String usernameOrEmail, String password, boolean rememberMe);

    /**
     * Logs out the given user.
     *
     * @param user the user to log out, not null.
     * @throws NotFoundException if the user is not in the system.
     */
    void logout(User user) throws NotFoundException;

}
