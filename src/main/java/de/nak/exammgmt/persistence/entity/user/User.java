/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity.user;

import de.nak.exammgmt.persistence.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Set;

/**
 * Entity to store the Users of the application. The system will differentiate between students, lecturers, etc.
 * by Role and not by subclass. A guest can by identified using the transient method isLoggedIn().
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Entity
public class User extends AbstractEntity {

    private String username;
    private String email;
    private String password;
    private String fullName;
    private boolean activated = false;
    private Role role;
    private UserSession currentUserSession;

    @Column(unique = true, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Transient
    public boolean isLoggedIn() {
        return true;
    }

    @Transient
    public UserSession getCurrentUserSession() {
        return currentUserSession;
    }

    public void setCurrentUserSession(UserSession currentUserSession) {
        this.currentUserSession = currentUserSession;
    }

    @Transient
    public boolean hasRights(Permission... rights) {
        return role.hasRights(rights);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    // TODO angucken
    @Transient
    public boolean hasRights(Set<Permission> rights) {

        System.out.println(username);
        return role.hasRights(rights);
    }

    @Override
    public String toString() {
        return fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
