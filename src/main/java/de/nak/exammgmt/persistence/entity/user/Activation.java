/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity.user;

import de.nak.exammgmt.persistence.entity.AbstractEntity;

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

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

}
