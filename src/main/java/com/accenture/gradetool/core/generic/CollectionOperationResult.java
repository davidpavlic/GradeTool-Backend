package com.accenture.gradetool.core.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionOperationResult<S, E> {

    private final Collection<S> successes;
    private final Collection<ResultError<E>> errors;

    public CollectionOperationResult() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public CollectionOperationResult(Collection<S> successes, Collection<ResultError<E>> errors) {
        this.successes = successes;
        this.errors = errors;
    }

    public void addSuccess(S success) {
        successes.add(success);
    }

    public void addError(ResultError<E> error) {
        errors.add(error);
    }

    public Collection<S> getSuccesses() {
        return successes;
    }

    public Collection<ResultError<E>> getErrors() {
        return errors;
    }

    public <S1, E1> CollectionOperationResult<S1, E1> map(Function<S, S1> successMapper, Function<E, E1> errorMapper) {
        Collection<S1> successes = this.successes.stream().map(successMapper).collect(Collectors.toSet());
        Collection<ResultError<E1>> errors = this.errors.stream().map(resultError -> new ResultError<>(errorMapper.apply(resultError.getValue()), resultError.getMessages())).collect(Collectors.toSet());

        return new CollectionOperationResult<>(successes, errors);
    }
}
