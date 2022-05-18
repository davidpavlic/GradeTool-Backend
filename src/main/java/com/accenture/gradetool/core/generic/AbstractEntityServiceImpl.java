package com.accenture.gradetool.core.generic;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;

public abstract class AbstractEntityServiceImpl<T extends AbstractEntity, R extends AbstractEntityRepository<T>> implements
    AbstractEntityService<T> {

    protected final Logger logger;
    protected final R repository;
    private String className;

    public AbstractEntityServiceImpl(Logger logger, R repository) {
        this.logger = logger;
        this.repository = repository;

        initClassName();
    }

    private void initClassName() {
        try {
            this.className = Class
                .forName(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName())
                .getSimpleName();
        } catch (ClassNotFoundException e) {
            this.className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        }
    }

    @Override
    public T findById(String id) throws NoSuchElementException {
        logger.debug("Attempting to find {} by ID '{}'", singleEntityName(), id);

        Optional<T> optional = repository.findByIdAndDeletedFalse(id);

        if (optional.isPresent()) {
            logger.debug("Found {}", singleEntityName());
            return optional.get();
        } else {
            logger.debug("{} not found", singleEntityName());
            throw new NoSuchElementException(String.format("%s with ID '%s' not found", singleEntityName(), id));
        }
    }

    @Override
    public Collection<T> findAll() {
        logger.debug("Attempting to find all {}", multipleEntitiesName());

        Collection<T> entities = repository.findAllByDeletedFalse();

        logger.debug("Found {}", multipleEntitiesName());

        return entities;
    }

    @Override
    public final T create(T entity) {
        return postCreate(doCreate(preCreate(startCreate(entity))));
    }

    private T startCreate(T entity) {
        logger.debug("Attempting to create new {}", singleEntityName());
        return entity;
    }

    protected T preCreate(T entity) {
        return entity;
    }

    private T doCreate(T entity) {
        entity.setId(null);

        entity = repository.saveAndFlush(entity);

        logger.debug("Created {}. New {}'s ID is '{}'", singleEntityName(), singleEntityName(), entity.getId());

        return entity;
    }

    protected T postCreate(T entity) {
        return entity;
    }

    @Override
    public final Collection<T> createAll(Collection<T> entities) {
        return postCreateAll(doCreateAll(preCreateAll(startCreateAll(entities))));
    }

    private Collection<T> startCreateAll(Collection<T> entities) {
        logger.debug("Attempting to create a collection of {}", multipleEntitiesName());
        return entities;
    }

    protected Collection<T> preCreateAll(Collection<T> entities) {
        return entities;
    }

    private Collection<T> doCreateAll(Collection<T> entities) {
        entities.forEach(entity -> entity.setId(null));

        entities = repository.saveAll(entities);
        repository.flush();

        logger.debug("Created {}", multipleEntitiesName());

        return entities;
    }

    protected Collection<T> postCreateAll(Collection<T> entities) {
        return entities;
    }

    @Override
    public final T save(T entity) {
        logger.debug("Attempting to save {}", singleEntityName());

        entity = repository.saveAndFlush(entity);

        logger.debug("Saved {}", singleEntityName());

        return entity;
    }

    @Override
    public final T updateById(String id, T entity) throws NoSuchElementException {
        return postUpdate(id, doUpdate(id, preUpdate(id, startUpdate(id, entity))));
    }

    private T startUpdate(String id, T entity) {
        logger.debug("Attempting to update {} by ID '{}'", singleEntityName(), id);

        if (repository.existsByIdAndDeletedFalse(id)) {
            return entity;
        } else {
            logger.debug("{} not found", singleEntityName());
            throw new NoSuchElementException(String.format("%s with ID '%s' not found", singleEntityName(), id));
        }
    }

    protected T preUpdate(String id, T entity) {
        return entity;
    }

    private T doUpdate(String id, T entity) {
        entity.setId(id);

        entity = repository.saveAndFlush(entity);

        logger.debug("Updated {}", singleEntityName());

        return entity;
    }

    protected T postUpdate(String id, T entity) {
        return entity;
    }

    @Override
    public final void deleteById(String id) throws NoSuchElementException {
        doDelete(id, preDelete(id, startDelete(id)));
    }

    @Override
    public final void delete(T entity) {
        doDelete(entity.getId(), preDelete(entity.getId(), startDelete(entity)));
    }

    private T startDelete(String id) {
        logger.debug("Attempting to delete {} by ID '{}'", singleEntityName(), id);

        Optional<T> optional = repository.findByIdAndDeletedFalse(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            logger.debug("{} not found", singleEntityName());
            throw new NoSuchElementException(String.format("%s with ID '%s' not found", singleEntityName(), id));
        }
    }

    private T startDelete(T entity) {
        logger.debug("Attempting to delete {} with ID '{}'", singleEntityName(), entity.getId());

        return entity;
    }

    protected T preDelete(String id, T entity) {
        return entity;
    }

    private void doDelete(String id, T entity) {
        entity.setId(id);

        entity.setDeleted(true);

        repository.saveAndFlush(entity);

        logger.debug("Deleted {}", singleEntityName());
    }

    protected final String singleEntityName() {
        return className;
    }

    protected final String multipleEntitiesName() {
        if (className.endsWith("y")) {
            return className.substring(0, className.length() - 1) + "ies";
        } else {
            return className + "s";
        }
    }

}
