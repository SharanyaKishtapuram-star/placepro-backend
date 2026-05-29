package com.placepro.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.placepro.dto.AuthResponse;
import com.placepro.dto.LoginRequest;
import com.placepro.dto.RegisterRequest;
import com.placepro.entity.User;
import com.placepro.repository.UserRepository;
import com.placepro.security.JwtUtil;
import com.placepro.service.AuthService;
import com.placepro.entity.Student;

import com.placepro.repository.StudentRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;
    

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            StudentRepository studentRepository,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;

        this.studentRepository =
                studentRepository;

        this.jwtUtil = jwtUtil;
    }

    @Override
    public String register(RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
        Student student = new Student();

        student.setUser(user);

        studentRepository.save(student);

        return "User Registered Successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(

                token,

                user.getId(),

                user.getRole().name()
        );
    }
}