/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.ExamDAO;
import de.nak.exammgmt.persistence.entity.Exam;

/**
 * Hibernate specific implementation of the {@link ExamDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ExamHibernateDAO extends HibernateDAO<Exam> implements ExamDAO {
}
