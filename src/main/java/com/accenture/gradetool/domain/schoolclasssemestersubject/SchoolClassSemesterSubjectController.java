package com.accenture.gradetool.domain.schoolclasssemestersubject;

import com.accenture.gradetool.domain.schoolclasssemestersubject.dto.SchoolClassSemesterSubjectMapper;
import com.accenture.gradetool.domain.schoolclasssemestersubject.dto.SchoolClassSemesterSubjectResponseDTO;
import com.accenture.gradetool.domain.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school-classes-semester-subject")
public class SchoolClassSemesterSubjectController {

    private final SchoolClassSemesterSubjectService schoolClassSemesterSubjectService;
    private final SchoolClassSemesterSubjectMapper schoolClassSemesterSubjectMapper;

    @Autowired
    public SchoolClassSemesterSubjectController(SchoolClassSemesterSubjectService schoolClassSemesterSubjectService, SchoolClassSemesterSubjectMapper schoolClassSemesterSubjectMapper) {
        this.schoolClassSemesterSubjectService = schoolClassSemesterSubjectService;
        this.schoolClassSemesterSubjectMapper = schoolClassSemesterSubjectMapper;
    }

    @GetMapping({"/{subjectId}", "/{subjectId}/"})
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<SchoolClassSemesterSubjectResponseDTO> getSchoolClassSemesterSubjectbySubjectId(@PathVariable String subjectId) {
        SchoolClassSemesterSubject schoolClassSemesterSubject = schoolClassSemesterSubjectService.find(subjectId);

        return new ResponseEntity<>(schoolClassSemesterSubjectMapper.toDTO(schoolClassSemesterSubject), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT') and @studentSchoolClassService.isStudentInSchoolClass(authentication.principal.student, #schoolClassId)")
    public ResponseEntity<SchoolClassSemesterSubjectResponseDTO> addSubject(
        @PathVariable String schoolClassId, @PathVariable String semesterId,
        @RequestBody Subject subject
    ) {
        SchoolClassSemesterSubject schoolClassSemesterSubject = schoolClassSemesterSubjectService
            .addSubjectToSchoolClassSemester(subject.getId(), schoolClassId, semesterId);

        return new ResponseEntity<>(schoolClassSemesterSubjectMapper.toDTO(schoolClassSemesterSubject), HttpStatus.OK);
    }

    @DeleteMapping("/{subjectId}")
    @PreAuthorize("hasRole('STUDENT') and @studentSchoolClassService.isStudentInSchoolClass(authentication.principal.student, #schoolClassId)")
    public ResponseEntity<Void> removeSubject(
        @PathVariable String schoolClassId, @PathVariable String semesterId,
        @PathVariable String subjectId
    ) {
        schoolClassSemesterSubjectService.removeSubjectFromSchoolClassSemester(subjectId, schoolClassId, semesterId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
