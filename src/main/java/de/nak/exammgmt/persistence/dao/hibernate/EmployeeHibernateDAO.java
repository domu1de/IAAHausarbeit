/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.EmployeeDAO;
import de.nak.exammgmt.persistence.entity.Employee;

/**
 * Hibernate specific implementation of the {@link EmployeeDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class EmployeeHibernateDAO extends HibernateDAO<Employee> implements EmployeeDAO {
}
