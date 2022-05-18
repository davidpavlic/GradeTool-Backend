package com.accenture.gradetool.core.generic;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface AbstractEntityService<T extends AbstractEntity> {

    /**
     * Guarantees to persist a new entity in the database and return the persisted object
     */
    T create(T entity);

    /**
     * Saves an entity to the database directly and returns the result. (May update an existing entity)
     */
    T save(T entity);

    /**
     * Creates a collection of entities directly to the database and returns the result
     */
    Collection<T> createAll(Collection<T> entities);

    T findById(String id) throws NoSuchElementException;

    Collection<T> findAll();

    T updateById(String id, T entity) throws NoSuchElementException;

    void deleteById(String id) throws NoSuchElementException;

    void delete(T entity);


}
