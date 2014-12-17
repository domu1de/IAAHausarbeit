/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service;

import de.nak.exammgmt.persistence.dao.ManipleDAO;
import de.nak.exammgmt.persistence.entity.Maniple;
import de.nak.exammgmt.service.exception.NotFoundException;

import java.util.List;

/**
 * Default implementation of the {@link ManipleService}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultManipleService implements ManipleService {

    private ManipleDAO manipleDAO;

    @Override
    public Maniple get(long manipleId) throws NotFoundException {
        Maniple maniple = manipleDAO.findById(manipleId);
        if (maniple == null) {
            throw new NotFoundException(Maniple.class);
        }
        return maniple;
    }

    @Override
    public List<Maniple> list() {
        return manipleDAO.findAll();
    }

    public void setManipleDAO(ManipleDAO manipleDAO) {
        this.manipleDAO = manipleDAO;
    }
}
