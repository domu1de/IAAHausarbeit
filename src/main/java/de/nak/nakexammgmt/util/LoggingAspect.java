/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Class to provide an aspect that logs when a method is entered and left.
 * Used for actions and interceptors.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Aspect to surround a method with logging.
     *
     * @param joinPoint the method to surround with logging
     * @return the result of the surrounded method
     * @throws Throwable
     */
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        LOGGER.debug("Entering:\t" + joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName() + "() using arguments: " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();

        LOGGER.debug("Leaving:\t" + joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName() + "().");

        return result;

    }
}
