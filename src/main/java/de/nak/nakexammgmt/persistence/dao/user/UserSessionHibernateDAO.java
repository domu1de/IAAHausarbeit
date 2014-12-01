/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.user;

import de.nak.nakexammgmt.persistence.dao.HibernateDAO;
import de.nak.nakexammgmt.persistence.entity.user.User;
import de.nak.nakexammgmt.persistence.entity.user.UserSession;

import java.util.List;

/**
 * Hibernate specific implementation of the {@link UserSessionDAO}
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class UserSessionHibernateDAO extends HibernateDAO<UserSession> implements UserSessionDAO {

    @Override
    public UserSession findByTokenAndUser(String token, Long userId) {
        return (UserSession) getCurrentSession().createQuery("FROM UserSession WHERE token = :token AND user.id = :userId")
                .setString("token", token)
                .setParameter("userId", userId)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserSession> findByUser(User user) {
        return getCurrentSession().createQuery("FROM UserSession WHERE user = :user ORDER BY updatedAt DESC")
                .setParameter("user", user)
                .list();
    }

    @Override
    public void deleteByUser(User user) {
        getCurrentSession().createQuery("DELETE FROM UserSession WHERE user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }

}
