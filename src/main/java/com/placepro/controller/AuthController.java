package com.placepro.controller;

import org.springframework.web.bind.annotation.*;

import com.placepro.dto.LoginRequest;
import com.placepro.dto.RegisterRequest;
import com.placepro.service.AuthService;
import com.placepro.dto.AuthResponse;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }
}