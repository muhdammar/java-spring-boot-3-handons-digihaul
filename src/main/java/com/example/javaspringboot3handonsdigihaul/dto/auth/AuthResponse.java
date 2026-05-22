package com.example.javaspringboot3handonsdigihaul.dto.auth;

import java.util.Set;

public class AuthResponse {

    private String token;
    private String tokenType;
    private long expiresIn;
    private String username;
    private Set<String> roles;

    public AuthResponse(String token, String tokenType, long expiresIn, String username, Set<String> roles) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }
}

