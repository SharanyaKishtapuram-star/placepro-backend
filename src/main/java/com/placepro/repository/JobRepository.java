package com.placepro.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.placepro.entity.Job;
import com.placepro.entity.User;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByRecruiter(User recruiter);
    Long countByRecruiter(User recruiter);
}