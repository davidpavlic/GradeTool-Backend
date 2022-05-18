package com.accenture.gradetool.domain.schoolclasssemestersubject.dto;

import com.accenture.gradetool.domain.exam.Exam;
import com.accenture.gradetool.domain.exam.dto.ExamDTO;
import com.accenture.gradetool.domain.schoolclasssemester.dto.SchoolClassSemesterDTO;
import com.accenture.gradetool.domain.subject.dto.SubjectDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolClassSemesterSubjectResponseDTO extends SchoolClassSemesterSubjectDTO {

    protected SchoolClassSemesterDTO schoolClassSemester;
    protected SubjectDTO subject;
    protected List<ExamDTO> exams;

}
