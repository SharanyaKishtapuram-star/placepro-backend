package com.placepro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placepro.repository.ApplicationRepository;
import com.placepro.repository.JobRepository;
import com.placepro.repository.UserRepository;
import com.placepro.entity.User;
import org.springframework.security.core.Authentication;

@RestController
public class RecruiterDashboardController {

    private final JobRepository jobRepository;

    private final ApplicationRepository
            applicationRepository;
    private final UserRepository userRepository;

    public RecruiterDashboardController(

            JobRepository jobRepository,

            ApplicationRepository
                    applicationRepository,

            UserRepository userRepository
    ){

        this.jobRepository = jobRepository;

        this.applicationRepository =
                applicationRepository;
        this.userRepository =
                userRepository;
    }

    @GetMapping("/api/recruiter/dashboard")
    public Map<String, Long>
    getRecruiterDashboard(
            Authentication authentication) {
    	String email =
    	        authentication.getName();

    	User recruiter =
    	        userRepository
    	                .findByEmail(email)
    	                .orElseThrow();

    	Long totalJobs =
    	        jobRepository
    	                .countByRecruiter(
    	                        recruiter
    	                );

    	Long totalApplications =
    	        applicationRepository.findAll()
    	                .stream()
    	                .filter(app ->

    	                        app.getJob()
    	                           .getRecruiter() != null

    	                        &&

    	                        app.getJob()
    	                           .getRecruiter()
    	                           .getId()
    	                           .equals(recruiter.getId())
    	                )
    	                .count();

    	Long shortlisted =
    	        applicationRepository.findAll()
    	                .stream()
    	                .filter(app ->

    	                        app.getJob()
    	                           .getRecruiter() != null

    	                        &&

    	                        app.getJob()
    	                           .getRecruiter()
    	                           .getId()
    	                           .equals(recruiter.getId())

    	                        &&

    	                        app.getStatus()
    	                           .equals("SHORTLISTED")
    	                )
    	                .count();

    	Long selected =
    	        applicationRepository.findAll()
    	                .stream()
    	                .filter(app ->

    	                        app.getJob()
    	                           .getRecruiter() != null

    	                        &&

    	                        app.getJob()
    	                           .getRecruiter()
    	                           .getId()
    	                           .equals(recruiter.getId())

    	                        &&

    	                        app.getStatus()
    	                           .equals("SELECTED")
    	                )
    	                .count();

        Map<String, Long> stats =
                new HashMap<>();

        stats.put("jobs", totalJobs);

        stats.put(
                "applications",
                totalApplications
        );

        stats.put(
                "shortlisted",
                shortlisted
        );

        stats.put(
                "selected",
                selected
        );

        return stats;
    }
}