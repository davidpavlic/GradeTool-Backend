package com.accenture.gradetool.domain.grade.dto;

import com.accenture.gradetool.core.generic.dto.DTO;
import com.accenture.gradetool.domain.exam.dto.ExamDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDTO extends DTO {

    protected double mark;

    protected ExamDTO exam;

}
