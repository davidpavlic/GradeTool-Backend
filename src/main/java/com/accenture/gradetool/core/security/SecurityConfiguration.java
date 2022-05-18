package com.accenture.gradetool.core.security;

import com.accenture.gradetool.domain.subject.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaAuditing(auditorAwareRef = "userAware")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTProperties jwtProperties;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Autowired
    public SecurityConfiguration(
        JWTProperties jwtProperties, ObjectMapper objectMapper, UserService userService,
        BCryptPasswordEncoder passwordEncoder, AuthenticationService authenticationService
    ) {
        this.jwtProperties = jwtProperties;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**", "/swagger.yaml")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/login", "/students").permitAll()
            .antMatchers(HttpMethod.GET, "/users/exists/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterAfter(loginHandler(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(authorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);

        return configurationSource;
    }

    @Bean
    public LoginHandler loginHandler() throws Exception {
        return new LoginHandler(new AntPathRequestMatcher("/login", "POST"), authenticationManager(), objectMapper, jwtProperties,
            authenticationService);
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter(authenticationService, jwtProperties);
    }

}
