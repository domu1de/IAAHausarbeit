/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Interceptor to register a preResultListener for pjax handling.
 * Looks for the X-PJAX http header
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class PjaxInterceptor extends AbstractInterceptor {

    public static final String SUFFIX = "_pjax";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        if ("true".equalsIgnoreCase(request.getHeader("X-PJAX"))) {
            invocation.addPreResultListener((invocation1, resultCode) -> {
                invocation1.setResultCode(resultCode + SUFFIX);
            });
        }

        return invocation.invoke();
    }

}
