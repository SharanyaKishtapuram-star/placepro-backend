package com.placepro.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.placepro.repository.ApplicationRepository;
import com.placepro.repository.JobRepository;
import com.placepro.service.DashboardService;
import java.util.List;
import com.placepro.repository.UserRepository;
import com.placepro.entity.User;
import org.springframework.security.core.Authentication;
import com.placepro.entity.Application;
import com.placepro.entity.Job;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final JobRepository jobRepository;

    private final ApplicationRepository
            applicationRepository;
    
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Long>
    getDashboardStats(
            Authentication authentication) {
        Map<String, Long> stats =
                new HashMap<>();

        long totalJobs =
                jobRepository.count();

        String email = authentication.getName();

        List<Application> applications =
                applicationRepository
                .findByStudentUserEmail(email);

        Long totalApplications =
                (long) applications.size();

        Long selectedCount =
                applications.stream()
                .filter(app ->
                        app.getStatus()
                        .equals("SELECTED"))
                .count();
        Long shortlistedCount =
                applications.stream()
                .filter(app ->
                        app.getStatus()
                        .equals("SHORTLISTED"))
                .count();

        stats.put(
                "jobs",
                totalJobs
        );

        stats.put(
                "applications",
                totalApplications
        );

        stats.put(
                "selected",
                selectedCount
        );
        stats.put(
                "shortlisted",
                shortlistedCount
        );

        return stats;
    }
    
    @Override
    public long getRecruiterJobCount(
            String email) {

        User recruiter =
                userRepository.findByEmail(email)
                .orElseThrow();

        return jobRepository
                .findByRecruiter(recruiter)
                .size();
    }
    @Override
    public Long getRecruiterTotalJobs(
            String email) {

        User recruiter =
                userRepository.findByEmail(email)
                .orElseThrow();

        return jobRepository
                .countByRecruiter(recruiter);
    }
    public List<Job> getRecruiterJobs(
            String email) {

        User recruiter =
                userRepository.findByEmail(email)
                .orElseThrow();

        return jobRepository
                .findByRecruiter(recruiter);
    }
}