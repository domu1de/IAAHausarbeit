/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

/**
 * Exception that indicated that the {@link de.nak.exammgmt.persistence.entity.user.User} has already been activated.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class AlreadyActivatedException extends Exception {

    public AlreadyActivatedException() {
        super("The given user has already been activated.");
    }
}
