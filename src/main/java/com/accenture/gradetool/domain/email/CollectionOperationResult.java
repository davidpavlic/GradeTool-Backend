package com.accenture.gradetool.domain.email;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionOperationResult<T> {

    private final Collection<T> successes;
    private final Collection<ResultError<T>> errors;

    public CollectionOperationResult() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public CollectionOperationResult(Collection<T> successes, Collection<ResultError<T>> errors) {
        this.successes = successes;
        this.errors = errors;
    }

    public CollectionOperationResult<T> addSuccess(T success) {
        successes.add(success);
        return this;
    }

    public CollectionOperationResult<T> addError(ResultError<T> error) {
        errors.add(error);
        return this;
    }

    public Collection<T> getSuccesses() {
        return successes;
    }

    public Collection<ResultError<T>> getErrors() {
        return errors;
    }
}
