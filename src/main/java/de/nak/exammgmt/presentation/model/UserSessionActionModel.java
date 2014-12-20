/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

import com.maxmind.geoip2.model.CityResponse;
import de.nak.exammgmt.persistence.entity.user.UserSession;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;
import java.util.List;

/**
 * Model to encapsulate UserSession entities against the presentation layer using the concept of form beans.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public class UserSessionActionModel {

    private List<UserSession> userSessions;
    private Parser userAgentParser;

    public UserSessionActionModel() {
        try {
            userAgentParser = new Parser();
        } catch (IOException e) {
            e.printStackTrace();
            // FIXME
        }
    }

    public List<UserSession> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(List<UserSession> userSessions) {
        this.userSessions = userSessions;
    }

    public Client parseUserAgent(String userAgentString) {
        return userAgentParser.parse(userAgentString);
    }

    public String formatIp(CityResponse cityResponse) {
        // FIXME bessere implementierung?
        String formattedString = "";

        if (cityResponse == null) {
            return null;
        }

        if (cityResponse.getCity().getName() != null) {
            formattedString = cityResponse.getCity().getName();
        }

        if (cityResponse.getMostSpecificSubdivision().getName() != null) {
            if (!formattedString.isEmpty()) {
                formattedString += ", ";
            }
            formattedString += cityResponse.getMostSpecificSubdivision().getName();
        }

        if (cityResponse.getCountry().getName() != null) {
            if (!formattedString.isEmpty()) {
                formattedString += ", ";
            }
            formattedString += cityResponse.getCountry().getName();
        }

        return !formattedString.isEmpty() ? formattedString : null;
    }

}
