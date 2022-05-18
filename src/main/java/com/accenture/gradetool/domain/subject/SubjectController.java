package com.accenture.gradetool.domain.subject;

import com.accenture.gradetool.domain.subject.dto.SubjectMapper;
import com.accenture.gradetool.domain.subject.dto.SubjectResponseDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper) {
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<SubjectResponseDTO>> findAll() {
        Collection<Subject> subjects = subjectService.findAll();

        return new ResponseEntity<>(subjectMapper.toDTO(subjects), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<SubjectResponseDTO> create(@RequestBody SubjectResponseDTO subjectResponseDTO) {
        Subject subject = subjectService.create(subjectMapper.fromDTO(subjectResponseDTO));

        return new ResponseEntity<>(subjectMapper.toDTO(subject), HttpStatus.CREATED);
    }

}
