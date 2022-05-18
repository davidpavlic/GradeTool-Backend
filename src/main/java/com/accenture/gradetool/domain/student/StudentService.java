package com.accenture.gradetool.domain.student;

import com.accenture.gradetool.core.generic.AbstractEntityService;

public interface StudentService extends AbstractEntityService<Student> {

    String NOT_FOUND = "student.not.found";

    String EMAIL_NOT_AVAILABLE = "email.not.available";

}
