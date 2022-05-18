package com.accenture.gradetool.core.security;

import com.accenture.gradetool.domain.subject.user.dto.UserDTO;

public interface AuthenticationService {

    UserDTO getAuthenticationResponse(String userId);

    void authenticate(String authenticationHeader);

}
