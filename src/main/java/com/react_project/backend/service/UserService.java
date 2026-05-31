package com.react_project.backend.service;

import com.react_project.backend.dto.response.UserResponse;
import com.react_project.backend.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.react_project.backend.entity.User;
import com.react_project.backend.dto.request.UserRequest;
import com.react_project.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {


    UserRepository userRepository;
    UserMapper userMapper;


    public UserResponse createUser(UserRequest userRequest){
        User user = userMapper.toUser(userRequest);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfor(int id){
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("don't find"));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(int id,UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("don't find"));
        user = userMapper.toUser(userRequest);
        user.setAdmin(false);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }


    public boolean isUsernameExited(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public User handleRegisterDTO(UserRequest userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("UserDTO must not be null");
        }
        User user = new User();
        user.setAdmin(false);
        user.setEmail(userRequest.getEmail());
        user.setFullName(userRequest.getFirstName() + userRequest.getLastName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
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
