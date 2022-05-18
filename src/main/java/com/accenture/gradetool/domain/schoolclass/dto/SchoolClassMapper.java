package com.accenture.gradetool.domain.schoolclass.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassMapper;
import com.accenture.gradetool.domain.schoolclass.SchoolClass;
import org.mapstruct.Mapper;

@Mapper(uses = {StudentSchoolClassMapper.class})
public interface SchoolClassMapper extends DTOMapper<SchoolClassResponseDTO, SchoolClass> {

}
