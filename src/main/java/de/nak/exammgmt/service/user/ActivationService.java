/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.user;

import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.AlreadyActivatedException;
import de.nak.exammgmt.service.exception.InvalidTokenException;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * Contract for a service to manage Activations.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ActivationService {

    /**
     * Creates an Activation for the given user including an email notification.
     *
     * @param user the user to start the activation for, not null.
     * @throws AlreadyActivatedException if the user could not be found.
     */
    void create(User user) throws AlreadyActivatedException;

    /**
     * Validates the given activation token.
     *
     * @param token the token to validate.
     * @return whether or not the token is valid.
     */
    boolean validate(String token);

    /**
     * Finishes the Activation for the given token by saving the password.
     *
     * @param token    the token for the activation.
     * @param password the new password for the user.
     * @throws InvalidTokenException if the provided token is invalid.
     * @throws NotFoundException if the user was not found.
     */
    void finish(String token, String password) throws InvalidTokenException, NotFoundException;

    /**
     * Cancels an ongoing Activation for the given user.
     *
     * @param user the user whose Activation shall be canceled, not null.
     */
    void cancel(User user);

}
