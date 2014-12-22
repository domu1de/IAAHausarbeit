/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.common;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Default implementation of the UrlProvider.
 * Request scoped.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultUrlProvider implements UrlProvider {

    private static final String ACTIVATION_PATH = "activate/";
    private static final String PASSWORD_REST_PATH = "password_reset/";
    private static final String STUDENT_PATH = "student/";

    private HttpServletRequest request;

    @Override
    public String baseUrl() {
        String url = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            url += ":" + request.getServerPort();
        }
        return url + request.getContextPath() + "/";
    }

    @Override
    public String requestUrl() {
        return baseUrl() + request.getRequestURI().replace(request.getContextPath() + "/", "");
    }

    @Override
    public String urlForActivation(String token) {
        return baseUrl() + ACTIVATION_PATH + urlSafePathSegment(nullSafe(token));
    }

    @Override
    public String urlForPasswordReset(String token) {
        return baseUrl() + PASSWORD_REST_PATH + nullSafe(token);
    }

    @Override
    public String urlForStudentAndCourse(long studentId, long courseId) {
        return baseUrl() + "course/" + courseId + "/" + STUDENT_PATH + studentId;
    }

    private String urlSafePathSegment(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String nullSafe(Object object) {
        return object != null ? "" + object : "";
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
