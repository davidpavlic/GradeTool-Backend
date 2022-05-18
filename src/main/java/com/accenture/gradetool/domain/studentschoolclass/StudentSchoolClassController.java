package com.accenture.gradetool.domain.studentschoolclass;

import com.accenture.gradetool.core.generic.CollectionOperationResult;
import com.accenture.gradetool.domain.student.dto.StudentDTO;
import com.accenture.gradetool.domain.student.dto.StudentMapper;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassMapper;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassResponseDTO;
import com.accenture.gradetool.domain.student.Student;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school-classes/{schoolClassId}/students")
public class StudentSchoolClassController {

    private final StudentSchoolClassService studentSchoolClassService;
    private final StudentSchoolClassMapper studentSchoolClassMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentSchoolClassController(StudentSchoolClassService studentSchoolClassService, StudentSchoolClassMapper studentSchoolClassMapper, StudentMapper studentMapper) {
        this.studentSchoolClassService = studentSchoolClassService;
        this.studentSchoolClassMapper = studentSchoolClassMapper;
        this.studentMapper = studentMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT') and @studentSchoolClassService.isStudentInSchoolClass(authentication.principal.student, #schoolClassId)")
    public ResponseEntity<CollectionOperationResult<StudentSchoolClassResponseDTO, StudentDTO>> addStudentsToSchoolClass(
        @PathVariable String schoolClassId,
        @RequestBody Collection<String> studentIds
    ) {
        CollectionOperationResult<StudentSchoolClass, Student> students = studentSchoolClassService.addStudentsToSchoolClass(schoolClassId, studentIds);

        return new ResponseEntity<>(students.map(studentSchoolClassMapper::toDTO, studentMapper::toDTO), HttpStatus.OK);
    }

}
