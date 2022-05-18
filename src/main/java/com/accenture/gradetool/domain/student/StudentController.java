package com.accenture.gradetool.domain.student;

import com.accenture.gradetool.domain.student.dto.StudentDTO;
import com.accenture.gradetool.domain.student.dto.StudentMapper;
import com.accenture.gradetool.domain.student.dto.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(
        StudentService studentService, StudentMapper studentMapper
    ) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> signUp(@RequestBody @Validated StudentDTO.Registration studentDTO) {
        Student student = studentService.create(studentMapper.fromDTO(studentDTO));

        return new ResponseEntity<>(studentMapper.toResponseDTO(student), HttpStatus.CREATED);
    }

    @GetMapping("/own")
    public ResponseEntity<StudentResponseDTO> getOwn(@AuthenticationPrincipal(expression = "student") Student student) {
        Student authenticated = studentService.findById(student.getId());

        return new ResponseEntity<>(studentMapper.toResponseDTO(authenticated), HttpStatus.OK);
    }

}
