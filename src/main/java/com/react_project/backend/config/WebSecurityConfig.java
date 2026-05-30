package com.react_project.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.react_project.backend.security.AuthEntryPointJwt;
import com.react_project.backend.security.AuthTokenFilter;
import com.react_project.backend.security.JwtUtils;
import com.react_project.backend.service.CustomUserDetailService;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    private final CustomUserDetailService userDetailService;
    private final AuthEntryPointJwt authEntryPointJwt;

    public WebSecurityConfig(CustomUserDetailService userDetailService, AuthEntryPointJwt authEntryPointJwt) {
        this.userDetailService = userDetailService;
        this.authEntryPointJwt = authEntryPointJwt;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(
            JwtUtils jwtUtil,
            CustomUserDetailService customUserDetailService) {
        return new AuthTokenFilter(jwtUtil, customUserDetailService);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtils jwtUtil,
            CustomUserDetailService customUserDetailService)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt))
                // Configure endpoint authorization
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/v1/auth/**", "/test/hello", "/api/v1/**")
                        .permitAll()

                        // All other endpoints require authentication
                        .anyRequest().authenticated())

                // Stateless session (required for JWT)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Add JWT filter before Spring Security's default filter
                .addFilterBefore(authenticationJwtTokenFilter(jwtUtil,
                        customUserDetailService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
