/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity.user;

import de.nak.nakexammgmt.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity to store Roles for the users with a name and a set of permissions.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Entity
public class Role extends AbstractEntity {

    private String name;
    private Set<Permission> permissions = new HashSet<>();

    @Transient
    public boolean hasRights(Permission... rights) {
        return permissions.containsAll(Arrays.asList(rights));
    }

    @Transient
    public boolean hasRights(Set<Permission> rights) {
        return permissions.containsAll(rights);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    public Set<Permission> getPermissions() {
        return permissions;
    }

    private void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
