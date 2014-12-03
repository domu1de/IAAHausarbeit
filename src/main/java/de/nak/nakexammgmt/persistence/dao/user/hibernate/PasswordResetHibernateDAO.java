/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.user.hibernate;

import de.nak.nakexammgmt.persistence.dao.hibernate.HibernateDAO;
import de.nak.nakexammgmt.persistence.dao.user.PasswordResetDAO;
import de.nak.nakexammgmt.persistence.entity.user.PasswordReset;
import de.nak.nakexammgmt.persistence.entity.user.User;

/**
 * Hibernate specific implementation of the {@link de.nak.nakexammgmt.persistence.dao.user.PasswordResetDAO}
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class PasswordResetHibernateDAO extends HibernateDAO<PasswordReset> implements PasswordResetDAO {

    @Override
    public PasswordReset findByToken(String token) {
        return (PasswordReset) getCurrentSession().createQuery("FROM PasswordReset WHERE token = :token")
                .setString("token", token)
                .uniqueResult();
    }

    @Override
    public boolean has(User user) {
        return getCurrentSession().createQuery("FROM PasswordReset WHERE user = :user")
                .setParameter("user", user)
                .uniqueResult() != null;
    }

    @Override
    public void deleteByUser(User user) {
        getCurrentSession().createQuery("DELETE FROM PasswordReset WHERE user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }


}
