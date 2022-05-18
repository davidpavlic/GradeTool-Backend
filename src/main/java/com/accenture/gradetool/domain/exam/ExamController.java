package com.accenture.gradetool.domain.exam;

import com.accenture.gradetool.domain.exam.dto.ExamMapper;
import com.accenture.gradetool.domain.exam.dto.ExamResponseDTO;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;
    private final ExamMapper examMapper;

    public ExamController(
        ExamService examService,
        ExamMapper examMapper
    ) {
        this.examService = examService;
        this.examMapper = examMapper;
    }

    @GetMapping({"", "/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Collection<ExamResponseDTO>> findAll() {
        Collection<Exam> exams = examService.findAll();

        return new ResponseEntity<>(examMapper.toDTO(exams), HttpStatus.OK);
    }

    @GetMapping({"/{examId}", "/{examId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ExamResponseDTO> findByExamId(@PathVariable String examId) {
        Exam exam = examService.findById(examId);

        return new ResponseEntity<>(examMapper.toDTO(exam), HttpStatus.OK);
    }

    @GetMapping({"/school-class-semester-subject/{schoolClassSemesterSubjectId}", "/school-class-semester-subject/{schoolClassSemesterSubjectId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Collection<ExamResponseDTO>> findBySchoolClassSemesterSubjectId(@PathVariable String schoolClassSemesterSubjectId) {
        Collection<Exam> exams = examService.findAll(schoolClassSemesterSubjectId);

        return new ResponseEntity<>(examMapper.toDTO(exams), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT') and @studentSchoolClassService.isStudentInSchoolClass(authentication.principal.student, #schoolClassId)")
    public ResponseEntity<ExamResponseDTO> create(
        @PathVariable String schoolClassId, @PathVariable String semesterId,
        @PathVariable String subjectId, @RequestBody @Validated ExamResponseDTO examResponseDTO
    ) {
        Exam exam = examService.create(schoolClassId, semesterId, subjectId, examMapper.fromDTO(examResponseDTO));

        return new ResponseEntity<>(examMapper.toDTO(exam), HttpStatus.CREATED);
    }

    @PostMapping({"/byDTO", "/byDTO/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ExamResponseDTO> createByDTO(@RequestBody @Validated ExamResponseDTO examResponseDTO) {
        examResponseDTO.setId(null);
        examResponseDTO.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Exam exam = examService.createByDTO(examMapper.fromDTO(examResponseDTO));

        return new ResponseEntity<>(examMapper.toDTO(exam), HttpStatus.CREATED);
    }

    @PutMapping({"", "/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ExamResponseDTO> update(@RequestBody @Validated ExamResponseDTO examResponseDTO) {
        examResponseDTO.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Exam exam = examService.update(examMapper.fromDTO(examResponseDTO));

        return new ResponseEntity<>(examMapper.toDTO(exam), HttpStatus.OK);
    }

    @DeleteMapping({"/{examId}", "/{examId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> delete(@PathVariable String examId) {
        examService.delete(examId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
