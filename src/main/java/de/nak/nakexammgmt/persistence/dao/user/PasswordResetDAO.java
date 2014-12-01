/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.user;

import de.nak.nakexammgmt.persistence.dao.DAO;
import de.nak.nakexammgmt.persistence.entity.user.PasswordReset;
import de.nak.nakexammgmt.persistence.entity.user.User;

/**
 * Data Access Object to provide persisted {@link PasswordReset} entities.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface PasswordResetDAO extends DAO<PasswordReset> {

    /**
     * Finds the password reset for the given token.
     *
     * @param token the token for the password reset.
     * @return the password reset for the token or null.
     */
    PasswordReset findByToken(String token);

    /**
     * Checks whether or not the given user has an active password reset.
     *
     * @param user the user to check for.
     * @return  true if a password reset is present or false if not.
     */
    boolean has(User user);

    /**
     * Deletes the password reset for the given user.
     *
     * @param user the user whose password reset should be deleted.
     */
    void deleteByUser(User user);

}
