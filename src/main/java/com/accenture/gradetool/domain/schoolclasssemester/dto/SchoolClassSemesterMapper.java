package com.accenture.gradetool.domain.schoolclasssemester.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.schoolclasssemester.SchoolClassSemester;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolClassSemesterMapper extends DTOMapper<SchoolClassSemesterDTO, SchoolClassSemester> {


}
