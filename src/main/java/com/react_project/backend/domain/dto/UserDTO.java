package com.react_project.backend.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserDTO {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
