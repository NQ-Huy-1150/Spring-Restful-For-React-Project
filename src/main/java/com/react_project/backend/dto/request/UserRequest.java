package com.react_project.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRequest {
    @Size(min = 3, message = "FirstName phải có tối thiểu 3 kí tự !")
    private String firstName;
    private String lastName;
    @Size(min = 5, message = "username phải có tối thiểu 5 kí tự !")
    private String username;

    @Email(message = "Email không hợp lệ !", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    private String password;

    @Size(min = 10, message = "phone number not valid")
    private String phoneNumber;

    @Size(min = 3, message = "ConfirmPassword phải có tối thiểu 3 kí tự !")
    private String confirmPassword;

    private boolean isAdmin;

}
