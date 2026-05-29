package com.placepro.service.impl;

import org.springframework.stereotype.Service;

import com.placepro.dto.StudentRequest;
import com.placepro.entity.Student;
import com.placepro.entity.User;
import com.placepro.repository.StudentRepository;
import com.placepro.repository.UserRepository;
import com.placepro.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            UserRepository userRepository
    ) {

        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String saveStudentProfile(
            Long userId,
            StudentRequest request
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        Student student = studentRepository
                .findByUser(user)
                .orElse(new Student());

        student.setStudentName(request.getStudentName());
        student.setCollegeName(request.getCollegeName());
        student.setBranch(request.getBranch());
        student.setSkills(request.getSkills());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setResumeUrl(request.getResumeUrl());

        student.setUser(user);

        studentRepository.save(student);

        return "Student Profile Saved";
    }
}