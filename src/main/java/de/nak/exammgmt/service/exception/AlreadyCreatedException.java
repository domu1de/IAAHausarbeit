/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

/**
 * Exception that indicates that the entity to create already exists.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class AlreadyCreatedException extends Exception implements TransactionSensitiveException {

    public AlreadyCreatedException() {
        super("The provided entity has already been created.");
    }

    public AlreadyCreatedException(String message) {
        super(message);
    }

}
