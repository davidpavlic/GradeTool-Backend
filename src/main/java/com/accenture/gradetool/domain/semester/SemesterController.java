package com.accenture.gradetool.domain.semester;

import com.accenture.gradetool.domain.semester.dto.SemesterMapper;
import com.accenture.gradetool.domain.semester.dto.SemesterResponseDTO;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    private final SemesterService semesterService;
    private final SemesterMapper semesterMapper;

    public SemesterController(SemesterService semesterService, SemesterMapper semesterMapper) {
        this.semesterService = semesterService;
        this.semesterMapper = semesterMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<SemesterResponseDTO>> findAll() {
        Collection<Semester> semesters = semesterService.findAllSorted();

        return new ResponseEntity<>(semesterMapper.toDTO(semesters), HttpStatus.OK);
    }

}
