package com.accenture.gradetool.core.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;
    private final JWTProperties jwtProperties;

    public AuthorizationFilter(AuthenticationService authenticationService, JWTProperties jwtProperties) {
        this.authenticationService = authenticationService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String authToken = request.getHeader(jwtProperties.getHeaderName());

        authenticationService.authenticate(authToken);

        filterChain.doFilter(request, response);
    }

}
