/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

/**
 * RuntimeException to indicate that the provided entity misses the required ID.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class MissingIdException extends RuntimeException implements TransactionSensitiveException {

    public MissingIdException() {
        super("The provided entity must have an ID.");
    }
}
