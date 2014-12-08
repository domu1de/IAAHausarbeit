/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.user;

import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.InvalidTokenException;
import de.nak.exammgmt.service.exception.NotFoundException;

/**
 * Contract for a service to manage PasswordResets.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface PasswordResetService {

    /**
     * Initiates the PasswordReset process for the user with the given credentials.
     *
     * @param usernameOrEmail the username or email of the user whose password will be reset.
     * @throws NotFoundException if no user was found.
     */
    void initResetProcess(String usernameOrEmail) throws NotFoundException;

    /**
     * Validates the request to reset the password by the given token.
     *
     * @param token the token of the PasswordReset.
     * @return whether or not the request is valid.
     */
    boolean validateResetRequest(String token);

    /**
     * Finishes the PasswordReset process and sets the new password to the user.
     *
     * @param token    token to identify the PasswordReset.
     * @param password the new password.
     * @throws InvalidTokenException if an invalid token was provided.
     * @throws NotFoundException if the corresponding user could not be found.
     */
    void finishPasswordReset(String token, String password) throws InvalidTokenException, NotFoundException;

    /**
     * Cancels an active PasswordReset process for the given user.
     *
     * @param user the user whose PasswordReset will be canceled, not null.
     */
    void cancelResetProcess(User user);

}
