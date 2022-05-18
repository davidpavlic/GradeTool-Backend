package com.accenture.gradetool.domain.subject.user;

import com.accenture.gradetool.domain.student.Student;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.user = user;

        String role = user instanceof Student ? "ROLE_STUDENT" : "ROLE_USER";

        authorities = Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.isDeleted();
    }

    public User getUser() {
        return user;
    }

    public Student getStudent() {
        if (user instanceof Student) {
            return (Student) user;
        }

        throw new Error("authenticated User is not a Student");
    }

}
