/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import de.nak.nakexammgmt.presentation.model.ResetDatabaseModel;
import de.nak.nakexammgmt.service.common.ResetDatabaseService;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class ResetDatabaseAction extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetDatabaseAction.class);

    private String password;
    private ResetDatabaseModel resetDatabaseModel = new ResetDatabaseModel();
    private ResetDatabaseService resetDatabaseService;

    @Override
    public String execute() throws Exception {
        if(isPost()) {
            if (password != null && password.equals(resetDatabaseModel.getPassword())) {
                resetDatabaseService.resetDatabase();
                addActionMessage(getText("txt.resetDatabase.success"));
                LOGGER.info("Database reset");
            }
        }
        return SUCCESS;
    }

    private boolean isPost() {
        HttpServletRequest context = ServletActionContext.getRequest();
        return context.getMethod().equalsIgnoreCase("post");
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