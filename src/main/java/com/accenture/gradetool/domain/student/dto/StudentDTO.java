package com.accenture.gradetool.domain.student.dto;

import com.accenture.gradetool.domain.subject.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO extends UserDTO {

    protected String firstName;

    @Getter
    @Setter
    public static class Registration extends UserDTO.Registration {
        protected String firstName;
    }

}
