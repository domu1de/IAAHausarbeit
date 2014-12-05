/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import de.nak.nakexammgmt.presentation.model.ResetDatabaseModel;
import de.nak.nakexammgmt.service.common.ResetDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Action to manage the reset of the database.
 * Needs a configuration for a password.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ResetDatabaseAction extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetDatabaseAction.class);

    private String password;
    private ResetDatabaseModel resetDatabaseModel = new ResetDatabaseModel();
    private ResetDatabaseService resetDatabaseService;

    public String show() {
        return SUCCESS;
    }

    public String reset() throws Exception {
        if (password != null && password.equals(resetDatabaseModel.getPassword())) {
            resetDatabaseService.resetDatabase();
            addActionMessage(getText("txt.resetDatabase.success"));
            LOGGER.info("Database reset");
        }
        return SUCCESS;
    }

    public ResetDatabaseModel getResetDatabaseModel() {
        return resetDatabaseModel;
    }

    public void setResetDatabaseModel(ResetDatabaseModel resetDatabaseModel) {
        this.resetDatabaseModel = resetDatabaseModel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResetDatabaseService getResetDatabaseService() {
        return resetDatabaseService;
    }

    public void setResetDatabaseService(ResetDatabaseService resetDatabaseService) {
        this.resetDatabaseService = resetDatabaseService;
    }
}
