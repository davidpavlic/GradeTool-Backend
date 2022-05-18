package com.accenture.gradetool.domain.schoolclass.dto;

import com.accenture.gradetool.core.generic.dto.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolClassDTO extends DTO {

    protected String name;
    protected int startYear;
    protected int endYear;

}
