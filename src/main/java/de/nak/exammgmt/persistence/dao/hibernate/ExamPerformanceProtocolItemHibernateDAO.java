/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.ExamPerformanceProtocolItemDAO;
import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem;

/**
 * Hibernate specific implementation of the {@link ExamPerformanceProtocolItemDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ExamPerformanceProtocolItemHibernateDAO extends HibernateDAO<ExamPerformanceProtocolItem>
        implements ExamPerformanceProtocolItemDAO {

    @Override
    public ExamPerformanceProtocolItem findByOldExamPerformance(ExamPerformance examPerformance) {
        return (ExamPerformanceProtocolItem) getCurrentSession().createQuery("FROM ExamPerformanceProtocolItem WHERE oldExamPerformance = :exam_performance")
                .setParameter("exam_performance", examPerformance)
                .uniqueResult();
    }

}
