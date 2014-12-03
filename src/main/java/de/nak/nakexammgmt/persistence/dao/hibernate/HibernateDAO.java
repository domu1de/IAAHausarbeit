/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.hibernate;

import de.nak.nakexammgmt.persistence.dao.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Generic Hibernate specific Data Access Object to provide a default implementation for most of the methods.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public abstract class HibernateDAO<T> implements DAO<T> {

    private SessionFactory sessionFactory;
    private Class<T> modelClass;

    @Override
    public void save(T t) {
        getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(Long id) {
        return (T) getCurrentSession().get(getModelClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + getTableName()).list();
    }

    @Override
    public boolean has(Long id) {
        return findById(id) != null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected final Class<T> getModelClass() {
        if (modelClass == null) {
            Class clazz = getClass();
            while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
                clazz = clazz.getSuperclass();
            }
            modelClass = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return modelClass;
    }

    protected String getTableName() {
        return getModelClass().getName();
    }

}
