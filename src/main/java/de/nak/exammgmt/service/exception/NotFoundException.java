/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

/**
 * Exception to indicate, that the requested entity could not be found.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class NotFoundException extends Exception implements TransactionSensitiveException {

    public NotFoundException(Class entityClazz) {
        super("The " + entityClazz.getSimpleName() + " entity could not be found.");
    }

}
