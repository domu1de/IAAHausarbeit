/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nak.exammgmt.persistence.entity.user.User;

/**
 * Custom BaseAction for this application to provide result-strings and a currentUser which will be set using an
 * Interceptor.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public abstract class BaseAction extends ActionSupport {

    protected static final String NOT_FOUND = "404";
    protected static final String REDIRECT_WELCOME = "redirect_welcome";

    protected static final String INDEX = "index";
    protected static final String SHOW = "show";
    protected static final String EDIT = "edit";
    protected static final String NEW = "new";
    protected static final String CREATE = "create";
    protected static final String UPDATE = "update";
    protected static final String REMOVE = "remove";

    protected User currentUser;
    protected String returnTo;

    @Override
    public String execute() throws Exception {
        return NOT_FOUND;
    }

    public String index() throws Exception {
        return NOT_FOUND;
    }

    public String create() throws Exception {
        return NOT_FOUND;
    }

    public String editNew() throws Exception {
        return NOT_FOUND;
    }

    public String edit() throws Exception {
        return NOT_FOUND;
    }

    public String show() throws Exception {
        return NOT_FOUND;
    }

    public String update() throws Exception {
        return NOT_FOUND;
    }

    public String remove() throws Exception {
        return NOT_FOUND;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getReturnTo() {
        return returnTo;
    }

    public void setReturnTo(String returnTo) {
        this.returnTo = returnTo;
    }
}
