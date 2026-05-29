package com.placepro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placepro.entity.User;
import com.placepro.entity.Role;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Long countByRole(Role role);
}