package com.accenture.gradetool.domain.semester;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemesterServiceImpl extends AbstractEntityServiceImpl<Semester, SemesterRepository> implements SemesterService {

    @Autowired
    public SemesterServiceImpl(Logger logger, SemesterRepository repository) {
        super(logger, repository);
    }

    @Override
    protected Collection<Semester> preCreateAll(Collection<Semester> semesters) {
        return semesters.stream().filter(semester -> !repository.existsByStartDateAndDeletedFalse(semester.getStartDate()))
            .collect(Collectors.toSet());
    }

    @Override
    public Collection<Semester> findAllSorted() {
        return repository.findAllByDeletedFalseOrderByStartDateDesc();
    }

    @Override
    public Collection<Semester> findAllInRange(LocalDate start, LocalDate end) {
        return repository.findAllInRange(start, end);
    }
}

