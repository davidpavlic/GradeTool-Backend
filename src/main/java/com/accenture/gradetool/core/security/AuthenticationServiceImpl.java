package com.accenture.gradetool.core.security;

import com.accenture.gradetool.domain.student.dto.StudentMapper;
import com.accenture.gradetool.domain.subject.user.dto.UserDTO;
import com.accenture.gradetool.domain.subject.user.dto.UserMapper;
import com.accenture.gradetool.domain.subject.user.UserDetailsImpl;
import com.accenture.gradetool.domain.subject.user.UserService;
import com.accenture.gradetool.domain.student.Student;
import com.accenture.gradetool.domain.subject.user.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final JWTProperties jwtProperties;
    private final Logger logger;

    public AuthenticationServiceImpl(
        UserService userService,
        UserMapper userMapper, StudentMapper studentMapper, JWTProperties jwtProperties, Logger logger
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.studentMapper = studentMapper;
        this.jwtProperties = jwtProperties;
        this.logger = logger;
    }

    @Override
    @Transactional
    public UserDTO getAuthenticationResponse(String userId) {
        logger.debug("Preparing authentication response");

        User user = userService.findById(userId);

        if (user instanceof Student) {
            Student student = (Student) user;

            return studentMapper.toResponseDTO(student);
        } else {
            return userMapper.toResponseDTO(user);
        }
    }

    @Override
    public void authenticate(String authToken) {
        if (authToken != null) {
            logger.debug("Authentication from Bearer token");
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(authToken));
        }
    }

    private Authentication getAuthentication(String authToken) {
        if (authToken.startsWith(jwtProperties.getTokenPrefix())) {
            try {
                String userId = parseSubject(authToken);

                UserDetails userDetails = new UserDetailsImpl(userService.findById(userId));

                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            } catch (JwtException | NoSuchElementException exception) {
                logger.debug("Exception", exception);
            }
        }

        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    private String parseSubject(String header) throws JwtException {
        return Jwts.parser()
            .setSigningKey(jwtProperties.getSecret())
            .parseClaimsJws(header.replace(jwtProperties.getTokenPrefix() + " ", ""))
            .getBody()
            .getSubject();
    }
}
