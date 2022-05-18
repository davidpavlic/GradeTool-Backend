package com.accenture.gradetool.domain.studentschoolclass;

import com.accenture.gradetool.core.error.NotUniqueException;
import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import com.accenture.gradetool.core.generic.CollectionOperationResult;
import com.accenture.gradetool.core.generic.ResultError;
import com.accenture.gradetool.domain.schoolclass.SchoolClassService;
import com.accenture.gradetool.domain.student.StudentService;
import com.accenture.gradetool.domain.schoolclass.SchoolClass;
import com.accenture.gradetool.domain.student.Student;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentSchoolClassService")
public class StudentSchoolClassServiceImpl extends AbstractEntityServiceImpl<StudentSchoolClass, StudentSchoolClassRepository> implements
    StudentSchoolClassService {

    private final StudentService studentService;
    private final SchoolClassService schoolClassService;

    @Autowired
    public StudentSchoolClassServiceImpl(
        Logger logger, StudentSchoolClassRepository repository, StudentService studentService,
        SchoolClassService schoolClassService
    ) {
        super(logger, repository);
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
    }

    @Override
    public StudentSchoolClass enrollInSchoolClass(String schoolClassId, Student student) {
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId);

        if (!isStudentInSchoolClass(student, schoolClassId)) {

            return repository
                .save(new StudentSchoolClass().setStudent(student).setSchoolClass(schoolClass));
        } else {
            throw new NotUniqueException("Student is already in SchoolClass");
        }
    }

    @Override
    public boolean isStudentInSchoolClass(Student student, String schoolClassId) {
        return repository.existsBySchoolClassIdAndStudentAndDeletedFalse(schoolClassId, student);
    }

    @Override
    public void leaveSchoolClass(String schoolClassId, Student authenticated) {

    }

    @Override
    public CollectionOperationResult<StudentSchoolClass, Student> addStudentsToSchoolClass(String schoolClassId, Collection<String> studentIds) {
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId);

        CollectionOperationResult<StudentSchoolClass, Student> result = new CollectionOperationResult<>();

        studentIds.forEach(id -> {
            Student student;

            try {
                student = studentService.findById(id);
            } catch (NoSuchElementException e) {
                ResultError<Student> error = new ResultError<>(new Student().setId(id)).addMessage(e.getMessage());
                result.addError(error);
                return;
            }

            if (isStudentInSchoolClass(student, schoolClassId)) {
                result.addError(new ResultError<>(student).addMessage("Student is already in SchoolClass"));
            } else {
                StudentSchoolClass studentSchoolClass = repository
                    .save(new StudentSchoolClass().setSchoolClass(schoolClass).setStudent(student));

                result.addSuccess(studentSchoolClass);
            }
        });

        return result;
    }
}
