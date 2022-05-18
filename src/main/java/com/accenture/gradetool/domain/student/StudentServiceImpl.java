package com.accenture.gradetool.domain.student;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import com.accenture.gradetool.domain.subject.user.UserService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends AbstractEntityServiceImpl<Student, StudentRepository> implements StudentService {

    private final UserService userService;

    public StudentServiceImpl(Logger logger, StudentRepository repository, UserService userService) {
        super(logger, repository);
        this.userService = userService;
    }

    @Override
    protected Student preCreate(Student student) {
        userService.encodePassword(student);

        return student;
    }
}
