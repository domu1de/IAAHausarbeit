/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity.user;

import de.nak.exammgmt.persistence.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * Entity to store an ongoing Activation of a user with a token.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Entity
public class Activation extends AbstractEntity {

    private User user;
    private String token;
    private LocalDateTime expiresAt;

    @OneToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(nullable = false)
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Equals by: expiresAt, token and user.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activation that = (Activation) o;

        if (expiresAt != null ? !expiresAt.equals(that.expiresAt) : that.expiresAt != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (expiresAt != null ? expiresAt.hashCode() : 0);
        return result;
    }
}
