package com.accenture.gradetool.domain.studentschoolclass.dto;

import com.accenture.gradetool.domain.schoolclass.dto.SchoolClassDTO;
import com.accenture.gradetool.domain.student.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSchoolClassResponseDTO extends StudentSchoolClassDTO {

    protected StudentDTO student;
    protected SchoolClassDTO schoolClass;

}
