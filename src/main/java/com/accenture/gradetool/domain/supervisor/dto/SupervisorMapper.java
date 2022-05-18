package com.accenture.gradetool.domain.supervisor.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.supervisor.Supervisor;
import org.mapstruct.Mapper;

@Mapper
public interface SupervisorMapper extends DTOMapper<SupervisorDTO, Supervisor> {

}
