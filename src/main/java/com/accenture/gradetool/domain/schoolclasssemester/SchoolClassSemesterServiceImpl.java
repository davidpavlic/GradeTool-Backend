package com.accenture.gradetool.domain.schoolclasssemester;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassSemesterServiceImpl extends AbstractEntityServiceImpl<SchoolClassSemester, SchoolClassSemesterRepository> implements
    SchoolClassSemesterService {

    @Autowired
    public SchoolClassSemesterServiceImpl(Logger logger, SchoolClassSemesterRepository repository) {
        super(logger, repository);
    }

    @Override
    public SchoolClassSemester find(String schoolClassId, String semesterId) throws NoSuchElementException {
        return repository.findBySchoolClassIdAndSemesterId(schoolClassId, semesterId).orElseThrow();
    }
}
