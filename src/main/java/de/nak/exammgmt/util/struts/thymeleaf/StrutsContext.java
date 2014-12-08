/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.util.struts.thymeleaf;

import com.opensymphony.xwork2.LocaleProvider;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom WebContext for Struts for Thymeleaf integration.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class StrutsContext extends WebContext {

    /**
     * Name of the variable that contains the action.
     */
    public static final String ACTION_VARIABLE_NAME = "action";

    private final Object action;

    public StrutsContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, Object action) {
        super(request, response, servletContext);
        this.action = action;

        if (action instanceof LocaleProvider) {
            setLocale(((LocaleProvider) action).getLocale());
        }

        setVariable(ACTION_VARIABLE_NAME, action);
    }

    public Object getAction() {
        return action;
    }

}
