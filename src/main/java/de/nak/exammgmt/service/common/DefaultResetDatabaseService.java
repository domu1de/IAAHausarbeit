/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;

import org.hibernate.SessionFactory;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Default implementation of the ResetDatabaseService.
 * The SQL file to provide the needed queries will be injected as Resource.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultResetDatabaseService implements ResetDatabaseService {

    private SessionFactory sessionFactory;
    private Resource resource;
    private StringBuffer stringBuffer;

    @Override
    public void resetDatabase() {
        try {
            stringBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF8"));
            reader.lines().forEach(this::appendLine);
            sessionFactory.getCurrentSession().createSQLQuery(stringBuffer.toString()).executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends the given line to the stringBuffer.
     *
     * @param line the line.
     */
    private void appendLine(String line) {
        if (!line.startsWith("//") && !line.trim().isEmpty()) {
            stringBuffer.append(line.trim());
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
