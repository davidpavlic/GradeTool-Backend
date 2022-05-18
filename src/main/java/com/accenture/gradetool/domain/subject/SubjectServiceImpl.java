package com.accenture.gradetool.domain.subject;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends AbstractEntityServiceImpl<Subject, SubjectRepository> implements SubjectService {

    public SubjectServiceImpl(Logger logger, SubjectRepository repository) {
        super(logger, repository);
    }
}
