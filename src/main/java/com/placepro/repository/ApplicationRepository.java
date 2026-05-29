package com.placepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placepro.entity.Application;
import java.util.List;

public interface ApplicationRepository
extends JpaRepository<Application, Long> {

boolean existsByStudentIdAndJobId(
    Long studentId,
    Long jobId
);
List<Application> findByStudentUserEmail(
        String email
);
Long countByStatus(String status);

void deleteByJobId(Long jobId);
}