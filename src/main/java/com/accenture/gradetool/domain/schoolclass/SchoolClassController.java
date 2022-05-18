package com.accenture.gradetool.domain.schoolclass;

import com.accenture.gradetool.domain.schoolclass.dto.SchoolClassDTO;
import com.accenture.gradetool.domain.schoolclass.dto.SchoolClassMapper;
import com.accenture.gradetool.domain.schoolclass.dto.SchoolClassResponseDTO;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassMapper;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassResponseDTO;
import com.accenture.gradetool.domain.studentschoolclass.StudentSchoolClassService;
import com.accenture.gradetool.domain.student.Student;
import com.accenture.gradetool.domain.studentschoolclass.StudentSchoolClass;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school-classes")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;
    private final StudentSchoolClassService studentSchoolClassService;
    private final SchoolClassMapper schoolClassMapper;
    private final StudentSchoolClassMapper studentSchoolClassMapper;

    @Autowired
    public SchoolClassController(
        SchoolClassService schoolClassService,
        StudentSchoolClassService studentSchoolClassService, SchoolClassMapper schoolClassMapper,
        StudentSchoolClassMapper studentSchoolClassMapper
    ) {
        this.schoolClassService = schoolClassService;
        this.studentSchoolClassService = studentSchoolClassService;
        this.schoolClassMapper = schoolClassMapper;
        this.studentSchoolClassMapper = studentSchoolClassMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<SchoolClassResponseDTO>> findAll() {
        Collection<SchoolClass> schoolClasses = schoolClassService.findAll();

        return new ResponseEntity<>(schoolClassMapper.toDTO(schoolClasses), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<SchoolClassDTO> create(@RequestBody SchoolClassResponseDTO schoolClassResponseDTO) {
        SchoolClass schoolClass = schoolClassService.create(schoolClassMapper.fromDTO(schoolClassResponseDTO));

        return new ResponseEntity<>(schoolClassMapper.toDTO(schoolClass), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentSchoolClassResponseDTO> enroll(
        @PathVariable String id,
        @AuthenticationPrincipal(expression = "student") Student student
    ) {
        StudentSchoolClass studentSchoolClass = studentSchoolClassService.enrollInSchoolClass(id, student);

        return new ResponseEntity<>(studentSchoolClassMapper.toDTO(studentSchoolClass), HttpStatus.OK);
    }

}
