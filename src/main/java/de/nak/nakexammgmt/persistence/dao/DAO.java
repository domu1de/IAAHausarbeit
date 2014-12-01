/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.dao;

import java.util.List;

/**
 * Generic Data Access Object to specify a contract for common methods.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface DAO<T> {

    /**
     * Saves or updates the generic entity.
     *
     * @param t the entity.
     */
    public void save(T t);

    /**
     * Deletes the generic entity.
     *
     * @param t the entity.
     */
    public void delete(T t);

    /**
     * Finds the generic entity by its id.
     *
     * @param id the id of the entity.
     * @return  the generic entity.
     */
    public T findById(Long id);

    /**
     * Finds all of the generic entities.
     *
     * @return List of all entities of the generic type.
     */
    public List<T> findAll();

    /**
     * Checks whether or not a persisted entity for the given id exists.
     *
     * @param id the id to look for.
     * @return  true if an entity for the id exists or false if not.
     */
    public boolean has(Long id);

}
