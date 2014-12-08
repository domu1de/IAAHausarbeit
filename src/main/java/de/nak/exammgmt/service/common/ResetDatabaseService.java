/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;

/**
 * Contract for a service to reset the database to a testable state.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ResetDatabaseService {

    /**
     * Resets the database to a testable state.
     */
    void resetDatabase();
}
