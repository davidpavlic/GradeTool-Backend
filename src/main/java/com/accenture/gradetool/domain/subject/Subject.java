package com.accenture.gradetool.domain.subject;

import com.accenture.gradetool.core.generic.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject extends AbstractEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Subject setId(String id) {
        this.id = id;
        return this;
    }
}
