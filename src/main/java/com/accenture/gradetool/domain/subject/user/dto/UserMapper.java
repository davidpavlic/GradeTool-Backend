package com.accenture.gradetool.domain.subject.user.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.subject.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends DTOMapper<UserDTO, User> {

    @Override
    default User fromDTO(UserDTO dto) {
        throw new UnsupportedOperationException("Do not map a DTO directly to a User. Use it's subclasses instead");
    }

    UserDTO toResponseDTO(User user);
}
