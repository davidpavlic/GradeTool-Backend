package com.accenture.gradetool.domain.exam.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.exam.Exam;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapper extends DTOMapper<ExamResponseDTO, Exam> {

}
