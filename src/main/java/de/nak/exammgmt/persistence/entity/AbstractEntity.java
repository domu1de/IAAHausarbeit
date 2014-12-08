/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Abstract entity to provide a common identifier and timestamps for creation and updates.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@MappedSuperclass
public abstract class AbstractEntity {

    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    // TODO test protected or private.
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(nullable = false)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}