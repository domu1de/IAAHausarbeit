/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity.user;

import de.nak.exammgmt.persistence.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Entity to store a UserSession.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Entity
public class UserSession extends AbstractEntity {

    private String token;
    private User user;
    private String ip;
    private String userAgent;
    private LocalDateTime sudoEnabledAt;
    private boolean rememberMe = false;

    // TODO do
    public UserSession() {
    }

    @Transient
    public boolean isSudoNeeded() {
        long timeBetweenSudo = ChronoUnit.MINUTES.between(sudoEnabledAt, LocalDateTime.now());
        return timeBetweenSudo > 30;
    }

    public void sudo() {
        setSudoEnabledAt(LocalDateTime.now());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getSudoEnabledAt() {
        return sudoEnabledAt;
    }

    private void setSudoEnabledAt(LocalDateTime sudoEnabledAt) {
        this.sudoEnabledAt = sudoEnabledAt;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Transient
    public LocalDateTime getAccessedAt() {
        return getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSession that = (UserSession) o;

        if (!token.equals(that.token)) return false;
        if (!user.equals(that.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
