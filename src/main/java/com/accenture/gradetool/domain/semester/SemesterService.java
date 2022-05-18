package com.accenture.gradetool.domain.semester;

import com.accenture.gradetool.core.generic.AbstractEntityService;
import java.time.LocalDate;
import java.util.Collection;

public interface SemesterService extends AbstractEntityService<Semester> {

    String NOT_FOUND = "semester.not.found";

    Collection<Semester> findAllSorted();

    Collection<Semester> findAllInRange(LocalDate start, LocalDate end);

}
