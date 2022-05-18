package com.accenture.gradetool.domain.studentschoolclass;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import com.accenture.gradetool.domain.student.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSchoolClassRepository extends AbstractEntityRepository<StudentSchoolClass> {

    boolean existsBySchoolClassIdAndStudentAndDeletedFalse(String schoolClassId, Student student);

}
