package com.accenture.gradetool.domain.subject.user;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractEntityRepository<User> {

    Optional<User> findByEmailAndDeletedFalse(String email);

    boolean existsByEmailAndDeletedFalse(String email);

}
