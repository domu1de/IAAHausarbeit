/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Contract for a service to manage Maniples.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface ManipleService {

    Maniple get(long manipleId) throws NotFoundException;

    List<Maniple> list();

}