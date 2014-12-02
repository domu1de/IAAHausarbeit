/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.common;

import org.hibernate.SessionFactory;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultResetDatabaseService implements ResetDatabaseService {

    private SessionFactory sessionFactory;
    private Resource resource;

    @Override
    public void resetDatabase() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resource.getFile())); //TODO find better solution.
            reader.lines().forEach(this::executeStatement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeStatement(String statement) {
        sessionFactory.getCurrentSession().createSQLQuery(statement).executeUpdate();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
