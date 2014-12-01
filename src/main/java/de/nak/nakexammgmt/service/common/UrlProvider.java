/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.common;


/**
 * Contract for a service to provide the URLs of the system.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface UrlProvider {

    /**
     * Returns the base URL of the application.
     *
     * @return the base URL.
     */
    String baseUrl();

    /**
     * Returns the absolute request URL.
     *
     * @return the request URL.
     */
    String requestUrl();

    /**
     * Returns the Activation URL for the given token.
     *
     * @param token the token for the Activation.
     * @return the URL of the Activation.
     */
    String urlForActivation(String token);

    /**
     * Returns the PasswordReset URL for the given token.
     *
     * @param token the token for the PasswordReset.
     * @return the URL of the PasswordReset.
     */
    String urlForPasswordReset(String token);

}
