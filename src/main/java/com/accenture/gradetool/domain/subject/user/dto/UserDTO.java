package com.accenture.gradetool.domain.subject.user.dto;

import com.accenture.gradetool.core.generic.dto.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends DTO {

    protected String email;

    @Getter
    @Setter
    public static class Registration extends UserDTO {

        protected String password;
    }

}
