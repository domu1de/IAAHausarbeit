/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity.user;

import de.nak.nakexammgmt.persistence.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Set;

/**
 * Entity to store the Users of the application. The system will differentiate between students, teachers, etc.
 * by Role and not by subclass. A guest can by identified using the transient method isLoggedIn().
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Entity
public class User extends AbstractEntity {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    // TODO angucken
    @Transient
    public boolean hasRights(Set<Permission> rights) {
        System.out.println(username);
        return role.hasRights(rights);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
