package com.react_project.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.react_project.backend.domain.User;
import com.react_project.backend.domain.dto.UserDTO;
import com.react_project.backend.repository.UserRepository;
import com.react_project.backend.security.JwtUtil;
import com.react_project.backend.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth/v1")
public class AuthenticatonController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthenticatonController(AuthenticationManager authenticationManager, UserService userService,
            PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody UserDTO userDTO) {
        if (this.userService.isUsernameExited(userDTO.getUsername())) {
            return "User already exits!";
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = this.userService.handleRegisterDTO(userDTO);
        user = this.userService.handleSaveUser(user);
        return "Id: " + user.getId() + "username: " + user.getUsername();
    }

}
