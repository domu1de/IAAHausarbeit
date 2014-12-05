/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.presentation.action;

import com.maxmind.geoip2.GeoIp2Provider;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.model.CityResponse;
import de.nak.nakexammgmt.presentation.model.UserSessionModel;
import de.nak.nakexammgmt.service.authentication.AuthenticationService;
import de.nak.nakexammgmt.service.exception.NotFoundException;

import java.net.InetAddress;

/**
 * RESTful action to manage UserSession resources.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class UserSessionsAction extends BaseAction {

    private AuthenticationService authenticationService;
    private GeoIp2Provider geoIp2Provider;

    private UserSessionModel userSessionModel = new UserSessionModel();
    private Long userSessionId;

    public String index() {
        try {
            userSessionModel.setUserSessions(authenticationService.listUserSessions(getCurrentUser()));
        } catch (NotFoundException e) {
            // TODO: loggen? Dieser fehler dürfte niemals auftreten
            return ERROR;
        }
        return INDEX;
    }

    public String remove() {
        if (userSessionId == null) {
            return NOT_FOUND;
        }

        if (getCurrentUser().getCurrentUserSession().getId().equals(userSessionId)) {
            return NOT_FOUND; // TODO: oder anderes result?
        }

        try {
            authenticationService.revokeUserSession(userSessionId, getCurrentUser());
            return INDEX;
        } catch (Exception e) {
            // TODO: refactor.
        }

        return ERROR;
    }

    // TODO: exceptions
    public CityResponse locateIp(String ip) throws Exception {
        try {
            return geoIp2Provider.city(InetAddress.getByName(ip));
        } catch (AddressNotFoundException e) {
            return null;
        }
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public UserSessionModel getUserSessionModel() {
        return userSessionModel;
    }

    public void setUserSessionModel(UserSessionModel userSessionModel) {
        this.userSessionModel = userSessionModel;
    }

    public Long getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(Long userSessionId) {
        this.userSessionId = userSessionId;
    }

    public void setGeoIp2Provider(GeoIp2Provider geoIp2Provider) {
        this.geoIp2Provider = geoIp2Provider;
    }
}
