package com.placepro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placepro.entity.Student;
import com.placepro.entity.User;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByUser(User user);
}