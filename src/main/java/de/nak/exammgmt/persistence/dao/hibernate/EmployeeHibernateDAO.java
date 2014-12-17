/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.hibernate;

import de.nak.exammgmt.persistence.dao.EmployeeDAO;
import de.nak.exammgmt.persistence.entity.Employee;
import de.nak.exammgmt.persistence.entity.user.User;

/**
 * Hibernate specific implementation of the {@link EmployeeDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class EmployeeHibernateDAO extends HibernateDAO<Employee> implements EmployeeDAO {

    @Override
    public Employee findByUser(User user) {
        return (Employee) getCurrentSession().createQuery("FROM Employee WHERE user = :user")
                .setParameter("user", user)
                .uniqueResult();
    }

}
