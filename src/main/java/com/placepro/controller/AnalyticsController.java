package com.placepro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placepro.dto.AnalyticsResponse;
import com.placepro.repository.ApplicationRepository;
import com.placepro.repository.JobRepository;
import com.placepro.repository.StudentRepository;
import com.placepro.repository.UserRepository;
import com.placepro.entity.Role;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final StudentRepository
            studentRepository;

    private final UserRepository
            userRepository;

    private final JobRepository
            jobRepository;

    private final ApplicationRepository
            applicationRepository;

    public AnalyticsController(

            StudentRepository studentRepository,

            UserRepository userRepository,

            JobRepository jobRepository,

            ApplicationRepository applicationRepository) {

        this.studentRepository =
                studentRepository;

        this.userRepository =
                userRepository;

        this.jobRepository =
                jobRepository;

        this.applicationRepository =
                applicationRepository;
    }

    @GetMapping
    public AnalyticsResponse getAnalytics() {

        AnalyticsResponse response =
                new AnalyticsResponse();

        Long students =
                studentRepository.count();

        Long recruiters =
                userRepository.countByRole(
                        Role.RECRUITER
                );
        Long jobs =
                jobRepository.count();

        Long applications =
                applicationRepository.count();

        Long selected =
                applicationRepository
                        .countByStatus(
                                "SELECTED"
                        );

        Double placementPercentage =
                students == 0
                        ? 0.0
                        : (selected * 100.0)
                        / students;

        response.setTotalStudents(
                students
        );

        response.setTotalRecruiters(
                recruiters
        );

        response.setTotalJobs(
                jobs
        );

        response.setTotalApplications(
                applications
        );

        response.setSelectedStudents(
                selected
        );

        response.setPlacementPercentage(
                placementPercentage
        );

        return response;
    }
}