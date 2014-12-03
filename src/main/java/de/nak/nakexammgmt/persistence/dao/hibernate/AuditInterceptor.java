/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao.hibernate;

import de.nak.nakexammgmt.persistence.entity.AbstractEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Hibernate Interceptor to set current {@link LocalDateTime} on update and save.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class AuditInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        boolean modifiedState = false;
        LocalDateTime now = LocalDateTime.now();
        if (entity instanceof AbstractEntity) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createdAt".equals(propertyNames[i]) || "updatedAt".equals(propertyNames[i])) {
                    state[i] = now;
                    modifiedState = true;
                }
            }
        }
        return modifiedState;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof AbstractEntity) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("updatedAt".equals(propertyNames[i])) {
                    currentState[i] = LocalDateTime.now();
                    return true;
                }
            }
        }
        return false;
    }
}
