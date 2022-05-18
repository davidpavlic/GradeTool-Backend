package com.accenture.gradetool.domain.exam.dto;

import com.accenture.gradetool.core.generic.dto.DTO;
import com.accenture.gradetool.domain.schoolclasssemestersubject.dto.SchoolClassSemesterSubjectDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDTO extends DTO {

    protected String date;
    protected String name;
    protected double weight;
    protected SchoolClassSemesterSubjectDTO schoolClassSemesterSubject;

}
