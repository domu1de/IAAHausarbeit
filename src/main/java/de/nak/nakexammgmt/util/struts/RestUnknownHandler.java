/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.util.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.UnknownHandler;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This {@link UnknownHandler} implementation is responsible for handling unknown REST-methods.
 * This offers the possibility to use the {@link org.apache.struts2.dispatcher.mapper.Restful2ActionMapper}
 * without the need to implement all REST-methods.
 *
 * An unknown method will result in an HTTP 404.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class RestUnknownHandler implements UnknownHandler {

    private static final String RESULT_CODE_404 = "404";
    private static final Logger LOGGER = LoggerFactory.getLogger(RestUnknownHandler.class);

    @Override
    public ActionConfig handleUnknownAction(String namespace, String actionName) throws XWorkException {
        return null;
    }

    @Override
    public Result handleUnknownResult(ActionContext actionContext, String actionName, ActionConfig actionConfig, String resultCode) throws XWorkException {
        return null;
    }

    @Override
    public Object handleUnknownActionMethod(Object action, String methodName) throws NoSuchMethodException {
        LOGGER.info("Skip method [{}] in class [{}]", methodName, action.getClass().getSimpleName());
        return RESULT_CODE_404;
    }

}
