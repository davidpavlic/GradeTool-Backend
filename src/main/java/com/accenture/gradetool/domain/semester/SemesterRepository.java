package com.accenture.gradetool.domain.semester;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends AbstractEntityRepository<Semester> {

    boolean existsByStartDateAndDeletedFalse(LocalDate startDate);

    Collection<Semester> findAllByDeletedFalseOrderByStartDateDesc();

    @Query("FROM Semester WHERE startDate >= :start AND startDate <= :end AND deleted = false")
    Collection<Semester> findAllInRange(LocalDate start, LocalDate end);

}
