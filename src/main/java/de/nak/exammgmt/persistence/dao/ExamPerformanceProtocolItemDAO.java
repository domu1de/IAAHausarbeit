/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao;

import de.nak.exammgmt.persistence.entity.ExamPerformance;
import de.nak.exammgmt.persistence.entity.ExamPerformanceProtocolItem;

/**
 * Data Access Object to provide persisted {@link ExamPerformanceProtocolItem} entities.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public interface ExamPerformanceProtocolItemDAO extends DAO<ExamPerformanceProtocolItem> {

    /**
     * Finds the protocol item in which the given exam performance is the old exam performance.
     *
     * @param examPerformance the exam performance to look for
     * @return the protocol item
     */
    ExamPerformanceProtocolItem findByOldExamPerformance(ExamPerformance examPerformance);
}
