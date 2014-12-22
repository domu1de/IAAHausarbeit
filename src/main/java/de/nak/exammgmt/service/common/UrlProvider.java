/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;


/**
 * Contract for a service to provide the URLs of the system.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface UrlProvider {

    /**
     * Returns the base URL of the application.
     *
     * @return the base URL
     */
    String baseUrl();

    /**
     * Returns the absolute request URL.
     *
     * @return the request URL
     */
    String requestUrl();

    /**
     * Returns the ActivationAction URL for the given token.
     *
     * @param token the token for the ActivationAction
     * @return the URL of the ActivationAction
     */
    String urlForActivation(String token);

    /**
     * Returns the PasswordResetAction URL for the given token.
     *
     * @param token the token for the PasswordResetAction
     * @return the URL of the PasswordResetAction
     */
    String urlForPasswordReset(String token);

    /**
     * Returns the StudentAction URL for the given student and course.
     *
     * @param studentId the student
     * @param courseId the course
     * @return the URL for the StudentAction
     */
    String urlForStudentAndCourse(long studentId, long courseId);

}
