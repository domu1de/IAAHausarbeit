/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.ManipleDAO;
import de.nak.exammgmt.persistence.entity.Maniple;

/**
 * Hibernate specific implementation of the {@link ManipleDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ManipleHibernateDAO extends HibernateDAO<Maniple> implements ManipleDAO {
}
