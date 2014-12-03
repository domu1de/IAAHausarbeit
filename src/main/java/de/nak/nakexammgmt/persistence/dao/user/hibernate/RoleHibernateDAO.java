/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.user.hibernate;

import de.nak.nakexammgmt.persistence.dao.hibernate.HibernateDAO;
import de.nak.nakexammgmt.persistence.dao.user.RoleDAO;
import de.nak.nakexammgmt.persistence.entity.user.Role;

import java.util.List;

/**
 * Hibernate specific implementation of the {@link de.nak.nakexammgmt.persistence.dao.user.RoleDAO}
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class RoleHibernateDAO extends HibernateDAO<Role> implements RoleDAO {
    @Override
    public Role findByName(String name) {
        return (Role) getCurrentSession().createQuery("FROM Role WHERE name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAllWithoutGuest() {
        return getCurrentSession().createQuery("FROM Role WHERE name != 'guest'").list();
    }
}
