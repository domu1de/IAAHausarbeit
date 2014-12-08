/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.presentation.action.BaseAction;
import de.nak.exammgmt.service.authentication.AuthenticationService;

/**
 * Custom Interceptor to ensure that every reached {@link BaseAction} is processed with a valid current user.
 * This user is authenticated by the AuthenticationService and may be a guest user.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class AuthenticationInterceptor implements Interceptor {

    private AuthenticationService authenticationService;

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        User user = authenticationService.authenticate();

        if (actionInvocation.getAction() instanceof BaseAction) {
            ((BaseAction) actionInvocation.getAction()).setCurrentUser(user);
        }

        return actionInvocation.invoke();
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

}
