package com.chipnews.api.dtos;

public class LoginResponse {

    private String email;

    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
