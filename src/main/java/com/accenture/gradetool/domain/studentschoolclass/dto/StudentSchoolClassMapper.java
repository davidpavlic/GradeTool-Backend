package com.accenture.gradetool.domain.studentschoolclass.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.studentschoolclass.StudentSchoolClass;
import org.mapstruct.Mapper;

@Mapper
public interface StudentSchoolClassMapper extends DTOMapper<StudentSchoolClassResponseDTO, StudentSchoolClass> {

}
