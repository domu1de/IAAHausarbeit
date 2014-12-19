/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

/**
 * Interface to mark that an exception should cause a transaction rollback.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface TransactionSensitiveException {
}
