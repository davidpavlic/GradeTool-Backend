package com.accenture.gradetool.domain.studentschoolclass;

import com.accenture.gradetool.core.generic.CollectionOperationResult;
import com.accenture.gradetool.domain.student.Student;
import java.util.Collection;

public interface StudentSchoolClassService {

    StudentSchoolClass enrollInSchoolClass(String schoolClassId, Student student);

    void leaveSchoolClass(String schoolClassId, Student authenticated);

    CollectionOperationResult<StudentSchoolClass, Student> addStudentsToSchoolClass(String schoolClassId, Collection<String> studentIds);

    boolean isStudentInSchoolClass(Student student, String schoolClassId);

}