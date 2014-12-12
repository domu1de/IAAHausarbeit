/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.dao.user.hibernate;

import de.nak.exammgmt.persistence.dao.hibernate.HibernateDAO;
import de.nak.exammgmt.persistence.dao.user.UserDAO;
import de.nak.exammgmt.persistence.entity.user.User;

import java.util.List;

/**
 * Hibernate specific implementation of the {@link UserDAO}
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class UserHibernateDAO extends HibernateDAO<User> implements UserDAO {

    @Override
    public User findByUsernameOrEmail(String usernameOrEmail) {
        return (User) getCurrentSession().createQuery("FROM User where username = :username OR email = :email")
                .setString("username", usernameOrEmail)
                .setString("email", usernameOrEmail)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findInactiveUsers() {
        return getCurrentSession().createQuery("FROM User where activated = :activated")
                .setBoolean("activated", false)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findActiveUsers() {
        return getCurrentSession().createQuery("FROM User where activated = :activated")
                .setBoolean("activated", true)
                .list();
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException();
    }
}
