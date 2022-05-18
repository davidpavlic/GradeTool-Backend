package com.accenture.gradetool.domain.supervisor.dto;

import com.accenture.gradetool.core.generic.dto.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupervisorDTO extends DTO {

    protected String firstName;
    protected String lastName;
    protected String email;

}

