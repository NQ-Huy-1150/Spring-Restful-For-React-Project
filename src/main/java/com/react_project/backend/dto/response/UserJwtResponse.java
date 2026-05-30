package com.react_project.backend.dto.response;

public class UserJwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String username;
    private String email;
    private boolean isAdmin;

    public UserJwtResponse(String token, int id, String username, String email, boolean isAdmin) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
