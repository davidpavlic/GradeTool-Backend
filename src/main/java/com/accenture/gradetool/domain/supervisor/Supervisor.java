package com.accenture.gradetool.domain.supervisor;

import com.accenture.gradetool.core.generic.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "supervisor")
public class Supervisor extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Override
    public Supervisor setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Supervisor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Supervisor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Supervisor setEmail(String email) {
        this.email = email;
        return this;
    }
}
