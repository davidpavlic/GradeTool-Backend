package com.accenture.gradetool.domain.subject.user;

import com.accenture.gradetool.core.generic.AbstractEntityService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends AbstractEntityService<User>, UserDetailsService {

    boolean existsByEmail(String email);

    void changePassword(User user, Password password);

    void encodePassword(User user);

}
