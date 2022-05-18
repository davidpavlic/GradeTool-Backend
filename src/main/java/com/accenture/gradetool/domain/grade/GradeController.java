package com.accenture.gradetool.domain.grade;

import com.accenture.gradetool.domain.grade.dto.GradeMapper;
import com.accenture.gradetool.domain.grade.dto.GradeResponseDTO;
import com.accenture.gradetool.domain.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    public GradeController(GradeService gradeService, GradeMapper gradeMapper) {
        this.gradeService = gradeService;
        this.gradeMapper = gradeMapper;
    }

    @GetMapping({"/student/{studentId}", "/student/{studentId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Collection<GradeResponseDTO>> findAllByStudentId(@PathVariable String studentId){
        Collection<Grade> grades = gradeService.findAllByStudentId(studentId);
        return new ResponseEntity<>(gradeMapper.toDTO(grades), HttpStatus.OK);
    }

    @GetMapping({"/{gradeId}", "/{gradeId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<GradeResponseDTO> findAllById(@PathVariable String gradeId){
        Grade grade = gradeService.findById(gradeId);
        return new ResponseEntity<>(gradeMapper.toDTO(grade), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT') and @studentSchoolClassService.isStudentInSchoolClass(authentication.principal.student, #schoolClassId)")
    public ResponseEntity<GradeResponseDTO> create(
        @PathVariable String schoolClassId,
        @PathVariable String semesterId,
        @PathVariable String subjectId,
        @PathVariable String examId,
        @RequestBody @Validated GradeResponseDTO gradeResponseDTO,
        @AuthenticationPrincipal(expression = "student") Student student
    ) {
        Grade grade = gradeService.create(schoolClassId, semesterId, subjectId, examId, gradeMapper.fromDTO(gradeResponseDTO), student);

        return new ResponseEntity<>(gradeMapper.toDTO(grade), HttpStatus.CREATED);
    }

    @PostMapping({"/{mark}/{examId}/{studentId}", "/{mark}/{examId}/{studentId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<GradeResponseDTO> createByPath(@PathVariable double mark, @PathVariable String examId, @PathVariable String studentId) {
        Grade grade = gradeService.createByPath(mark, examId, studentId);

        return new ResponseEntity<>(gradeMapper.toDTO(grade), HttpStatus.CREATED);
    }

    @PutMapping({"/{gradeId}/{mark}", "/{gradeId}/{mark}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<GradeResponseDTO> update(@PathVariable String gradeId, @PathVariable double mark) {
        Grade grade = gradeService.update(gradeId, mark);

        return new ResponseEntity<>(gradeMapper.toDTO(grade), HttpStatus.OK);
    }

    @DeleteMapping({"/{gradeId}", "/{gradeId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> delete(@PathVariable String gradeId) {
        gradeService.delete(gradeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
