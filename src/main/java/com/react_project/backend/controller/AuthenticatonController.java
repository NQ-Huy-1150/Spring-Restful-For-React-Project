package com.react_project.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.react_project.backend.domain.User;
import com.react_project.backend.domain.UserDetailsImpl;
import com.react_project.backend.domain.dto.request.LoginRequest;
import com.react_project.backend.domain.dto.request.UserDTO;
import com.react_project.backend.domain.dto.response.UserJwtResponse;
import com.react_project.backend.domain.dto.response.MessageResponse;
import com.react_project.backend.security.JwtUtils;
import com.react_project.backend.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticatonController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtil;

    public AuthenticatonController(AuthenticationManager authenticationManager, UserService userService,
            PasswordEncoder encoder, JwtUtils jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
        return ResponseEntity.ok(new UserJwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), isAdmin));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        if (this.userService.isUsernameExited(userDTO.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("username is already taken!"));
        }
        if (this.userService.isEmailExisted(userDTO.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Email is already in use!"));
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = this.userService.handleRegisterDTO(userDTO);
        user = this.userService.handleSaveUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
