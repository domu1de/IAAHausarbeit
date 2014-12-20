/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.presentation.model.ResetDatabaseActionModel;
import de.nak.exammgmt.service.common.ResetDatabaseService;
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
    private ResetDatabaseActionModel resetDatabaseActionModel = new ResetDatabaseActionModel();
    private ResetDatabaseService resetDatabaseService;

    @Override
    public String show() {
        return SUCCESS;
    }

    public String reset() throws Exception {
        if (password != null && password.equals(resetDatabaseActionModel.getPassword())) {
            resetDatabaseService.resetDatabase();
            addActionMessage(getText("txt.resetDatabase.success"));
            LOGGER.info("Database reset");
        }
        return SUCCESS;
    }

    public ResetDatabaseActionModel getResetDatabaseActionModel() {
        return resetDatabaseActionModel;
    }

    public void setResetDatabaseActionModel(ResetDatabaseActionModel resetDatabaseActionModel) {
        this.resetDatabaseActionModel = resetDatabaseActionModel;
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
