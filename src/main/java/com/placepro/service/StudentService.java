package com.placepro.service;

import com.placepro.dto.StudentRequest;

public interface StudentService {

    String saveStudentProfile(
            Long userId,
            StudentRequest request
    );
}