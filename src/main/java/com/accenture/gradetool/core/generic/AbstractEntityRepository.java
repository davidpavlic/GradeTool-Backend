package com.accenture.gradetool.core.generic;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractEntityRepository<T extends AbstractEntity> extends JpaRepository<T, String> {

    Collection<T> findAllByDeletedFalse();

    Optional<T> findByIdAndDeletedFalse(String id);

    boolean existsByIdAndDeletedFalse(String id);

}
