package com.accenture.gradetool.domain.schoolclasssemestersubject.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.schoolclasssemestersubject.SchoolClassSemesterSubject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolClassSemesterSubjectMapper extends DTOMapper<SchoolClassSemesterSubjectResponseDTO, SchoolClassSemesterSubject> {

}
