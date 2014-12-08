/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.user;

import de.nak.exammgmt.persistence.dao.DAO;
import de.nak.exammgmt.persistence.entity.user.Activation;
import de.nak.exammgmt.persistence.entity.user.User;

/**
 * Data Access Object to provide persisted {@link Activation} entities.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ActivationDAO extends DAO<Activation> {

    /**
     * Finds the Activation for the given token.
     *
     * @param token the token for the activation.
     * @return the activation.
     */
    Activation findByToken(String token);

    /**
     * Checks whether or not the user has an ongoing activation.
     *
     * @param user the user to check for.
     * @return true if the user has an ongoing activation or false if not.
     */
    boolean has(User user);

    /**
     * Deletes the activation for the given user.
     *
     * @param user the user whose activation should be deleted.
     */
    void deleteByUser(User user);

}
