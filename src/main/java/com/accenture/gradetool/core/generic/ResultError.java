package com.accenture.gradetool.core.generic;

import java.util.HashSet;
import java.util.Set;

public class ResultError<T> {

    private final T value;
    private final Set<String> messages;

    public ResultError(T value) {
        this(value, new HashSet<>());
    }

    public ResultError(T value, Set<String> messages) {
        this.value = value;
        this.messages = messages;
    }

    public T getValue() {
        return value;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public ResultError<T> addMessage(String message) {
        messages.add(message);
        return this;
    }
}
