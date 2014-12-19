/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import de.nak.exammgmt.persistence.entity.user.Permission;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.presentation.action.BaseAction;
import de.nak.exammgmt.service.authorization.AuthorizationService;
import de.nak.exammgmt.service.common.UrlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Custom Interceptor to check whether or not the current user is authorized to invoke the reached action.
 * Action methods can be marked as protected using the {@link Protected} annotation.
 * This Interceptor needs to be placed after the {@link AuthenticationInterceptor}, to ensure the existence of a
 * current user.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class AuthorizationInterceptor extends AbstractInterceptor {

    private static final String DENIED = "denied";
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    private AuthorizationService authorizationService;
    private UrlProvider urlProvider;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Object action = actionInvocation.getAction();
        Set<Permission> permissions = getPermissions(actionInvocation);
        if (!(action instanceof BaseAction)) {
            if (permissions.size() > 0) {
                // TODO: check this
                LOGGER.warn(String.format(
                        "%s#%s is annotated with Protected, but the action is not of type [%s] as it's required",
                        action.getClass().getName(),
                        actionInvocation.getProxy().getMethod(),
                        BaseAction.class.getName()
                        )
                );
            }
            return actionInvocation.invoke();
        }

        User user = ((BaseAction) action).getCurrentUser();
        switch (authorizationService.accessGranted(permissions, user)) {
            case ACCESS_DENIED:
                return DENIED;
            case ACCESS_GRANTED:
                return actionInvocation.invoke();
            case LOGIN_NEEDED:
                ((BaseAction) action).setReturnTo(urlProvider.requestUrl());
                return Action.LOGIN;
            default:
                return Action.ERROR;
        }
    }

    /**
     * Gets a set of need permissions as defined by the action method.
     *
     * @param actionInvocation the calling action invocation
     * @return a set of needed permissions
     */
    private Set<Permission> getPermissions(ActionInvocation actionInvocation) {
        Class<?> actionClass = actionInvocation.getAction().getClass();
        String methodName = actionInvocation.getProxy().getMethod();
        Set<Permission> permissions = new HashSet<>();

        try {
            Method method = actionClass.getMethod(methodName);
            if (method.isAnnotationPresent(Protected.class)) {
                permissions.addAll(Arrays.asList(method.getAnnotation(Protected.class).value()));
            }
        } catch (NoSuchMethodException e) {
            // ignoring this allows us to not implement all rest methods in an action if not needed
            LOGGER.info("Skip method [{}] in class [{}]", methodName, actionClass.getName());
        }

        return permissions;
    }

    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    public void setUrlProvider(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
    }
}
