package com.accenture.gradetool.domain.subject.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.subject.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectMapper extends DTOMapper<SubjectResponseDTO, Subject> {

}
