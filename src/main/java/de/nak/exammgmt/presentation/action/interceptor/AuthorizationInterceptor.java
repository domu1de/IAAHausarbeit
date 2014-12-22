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
import de.nak.exammgmt.service.common.UrlProvider;
import org.apache.struts2.ServletActionContext;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    private UrlProvider urlProvider;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Protected methodAnnotation = getMethodAnnotation(actionInvocation);
        Protected classAnnotation = actionInvocation.getAction().getClass().getAnnotation(Protected.class);

        if (methodAnnotation == null && classAnnotation == null) {
            return actionInvocation.invoke();
        }

        if (!(actionInvocation.getAction() instanceof BaseAction)) {
            LOGGER.warn(String.format(
                            "%s#%s is annotated with @Protected, but the action is not of type [%s] as it's required",
                            actionInvocation.getAction().getClass().getName(),
                            actionInvocation.getProxy().getMethod(),
                            BaseAction.class.getName()
                    )
            );
            return actionInvocation.invoke();
        }

        BaseAction action = (BaseAction) actionInvocation.getAction();
        User user = action.getCurrentUser();

        boolean requireLogin = false;
        Set<Permission> permissions = new HashSet<>();

        if (classAnnotation != null) {
            requireLogin = classAnnotation.login();
            mergePermissions(permissions, classAnnotation.value());
        }

        if (methodAnnotation != null) {
            requireLogin = methodAnnotation.login();
            mergePermissions(permissions, methodAnnotation.value());
        }

        // If the user is not logged in as required, show login
        if (requireLogin && !user.isLoggedIn()) {
            action.setReturnTo(urlProvider.requestUrl());
            return Action.LOGIN;
        }

        // ACCESS DENIED? Show error and set 403 status
        if (!user.hasRights(permissions.toArray(new Permission[permissions.size()]))) {
            return sendAccessDenied(action);
        }

        return actionInvocation.invoke();
    }

    /**
     * Send an access denied error.
     *
     * @param action the calling aciton
     * @return result code
     */
    public String sendAccessDenied(BaseAction action) {
        action.addActionError(action.getText("txt.accessDenied"));
        ServletActionContext.getResponse().setStatus(403);
        return BaseAction.ERROR;
    }

    /**
     * Returns the {@link Protected} annotation if present, or null.
     *
     * @param actionInvocation the calling action invocation
     * @return annotation, or {@code null}
     */
    private Protected getMethodAnnotation(ActionInvocation actionInvocation) {
        Class<?> actionClass = actionInvocation.getAction().getClass();
        String methodName = actionInvocation.getProxy().getMethod();

        try {
            Method method = actionClass.getMethod(methodName);
            return method.getAnnotation(Protected.class);
        } catch (NoSuchMethodException e) {
            // ignoring this allows us to not implement all rest methods in an action if not needed
            LOGGER.info("Skip method [{}] in class [{}]", methodName, actionClass.getName());
        }

        return null;
    }

    private void mergePermissions(Set<Permission> permissions, Permission[] additionPermissions) {
        permissions.addAll(Arrays.asList(additionPermissions));
    }

    public void setUrlProvider(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
    }
}
