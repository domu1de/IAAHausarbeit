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

    /**
     * Return an instance of the given entity class with the given id.
     *
     * @param id identifier to set on the entity
     * @param entityClass class of the entity to create an instance of
     * @return an instance of the given class
     */
    @SuppressWarnings("unchecked")
    public static <T extends AbstractEntity> T withId(long id, Class<T> entityClass) {
        try {
            AbstractEntity entity = entityClass.newInstance();
            entity.setId(id);

            return (T) entity;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
