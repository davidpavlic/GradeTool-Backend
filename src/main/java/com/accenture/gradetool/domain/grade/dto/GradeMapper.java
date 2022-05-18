package com.accenture.gradetool.domain.grade.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.grade.Grade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper extends DTOMapper<GradeResponseDTO, Grade> {

}
