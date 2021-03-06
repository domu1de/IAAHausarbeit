/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action.interceptor;


import de.nak.exammgmt.persistence.entity.user.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to annotate an action method as protected with its required permissions.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Protected {

    /**
     * (Optional) The list of required permissions.
     *
     * @return required permissions
     */
    Permission[] value() default {};

    /**
     * (Optional) Whether the user need to be logged in.
     *
     * @return {@code true}, if user needs to be logged in.
     */
    boolean login() default false;

}
