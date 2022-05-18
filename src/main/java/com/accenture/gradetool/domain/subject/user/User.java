package com.accenture.gradetool.domain.subject.user;

import com.accenture.gradetool.core.generic.AbstractEntity;
import javax.persistence.Entity;

@Entity
public abstract class User extends AbstractEntity {

    protected String email;

    protected String password;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
