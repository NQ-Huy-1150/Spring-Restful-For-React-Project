package com.react_project.backend.service;

import org.springframework.stereotype.Service;

import com.react_project.backend.entity.User;
import com.react_project.backend.dto.request.UserDTO;
import com.react_project.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameExited(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public User handleRegisterDTO(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO must not be null");
        }
        User user = new User();
        user.setAdmin(false);
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFirstName() + userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public User handleSaveUser(User user) {
        user = this.userRepository.save(user);
        return user;
    }

    public boolean isEmailExisted(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
