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
 * Default implementation of the ResetDatabaseService.
 * The SQL file to provide the needed queries will be injected as Resource.
 *
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

    /**
     * Excecutes the given statement as SQLQuery.
     *
     * @param statement the SQLQuery.
     */
    private void executeStatement(String statement) {
        sessionFactory.getCurrentSession().createSQLQuery(statement).executeUpdate();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
