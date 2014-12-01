/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.common;

/**
 * Contract for a service to manage the ActionContext Session.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface SessionService {

    /**
     * Invalidates the current Session.
     */
    void invalidateSession();
}
