package com.accenture.gradetool.core.error;

public class ViolatedField {

    private String field;
    private Object value;

    public ViolatedField(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public ViolatedField setField(String field) {
        this.field = field;
        return this;
    }

    public Object getValue() {
        return value != null ? value : "null";
    }

    public ViolatedField setValue(Object value) {
        this.value = value;
        return this;
    }
}
