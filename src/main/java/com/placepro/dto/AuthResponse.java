package com.placepro.dto;

public class AuthResponse {

    private String token;

    private Long id;

    private String role;

    public AuthResponse(
            String token,
            Long id,
            String role
    ) {

        this.token = token;
        this.id = id;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}