package com.accenture.gradetool.domain.semester.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.semester.Semester;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SemesterMapper extends DTOMapper<SemesterResponseDTO, Semester> {

}
