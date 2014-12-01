/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.user;

import de.nak.nakexammgmt.persistence.dao.HibernateDAO;
import de.nak.nakexammgmt.persistence.entity.user.Activation;
import de.nak.nakexammgmt.persistence.entity.user.User;

/**
 * Hibernate specific implementation of the {@link ActivationDAO}
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class ActivationHibernateDAO extends HibernateDAO<Activation> implements ActivationDAO {

    @Override
    public Activation findByToken(String token) {
        return (Activation) getCurrentSession().createQuery("FROM Activation WHERE token = :token")
                .setString("token", token)
                .uniqueResult();
    }

    @Override
    public boolean has(User user) {
        return getCurrentSession().createQuery("FROM Activation WHERE user = :user")
                .setParameter("user", user)
                .uniqueResult() != null;
    }

    @Override
    public void deleteByUser(User user) {
        getCurrentSession().createQuery("DELETE FROM Activation WHERE user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }

}
