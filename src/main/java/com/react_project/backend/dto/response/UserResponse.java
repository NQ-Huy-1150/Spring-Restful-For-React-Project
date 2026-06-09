package com.react_project.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private int id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;

    private boolean isAdmin;
}
