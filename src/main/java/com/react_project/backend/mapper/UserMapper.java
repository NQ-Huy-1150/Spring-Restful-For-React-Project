package com.react_project.backend.mapper;

import com.react_project.backend.dto.request.UserRequest;
import com.react_project.backend.dto.response.UserResponse;
import com.react_project.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}
