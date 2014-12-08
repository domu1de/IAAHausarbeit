/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * Default implementation of the SessionService.
 * Session scoped. // TODO: session scope!
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class DefaultSessionService implements SessionService {
    @Override
    public void invalidateSession() {
        SessionMap session = (SessionMap) ActionContext.getContext().getSession();

        if (session != null) {
            session.invalidate();

            //renew servlet session
            session.put("renewServletSession", null);
            session.remove("renewServletSession");
        }
    }
}
