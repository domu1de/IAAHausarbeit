/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.ExamPerformanceDAO;
import de.nak.exammgmt.persistence.entity.ExamPerformance;

/**
 * Hibernate specific implementation of the {@link ExamPerformanceDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ExamPerformanceHibernateDAO extends HibernateDAO<ExamPerformance> implements ExamPerformanceDAO {
}
