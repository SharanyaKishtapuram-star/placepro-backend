package com.placepro.controller;

import org.springframework.web.bind.annotation.*;

import com.placepro.dto.StudentRequest;
import com.placepro.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(
            StudentService studentService
    ) {

        this.studentService = studentService;
    }

    @PostMapping("/save/{userId}")
    public String saveStudentProfile(
            @PathVariable Long userId,
            @RequestBody StudentRequest request
    ) {

        return studentService.saveStudentProfile(
                userId,
                request
        );
    }
}