package com.placepro.service;

import com.placepro.dto.AuthResponse;
import com.placepro.dto.LoginRequest;
import com.placepro.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}