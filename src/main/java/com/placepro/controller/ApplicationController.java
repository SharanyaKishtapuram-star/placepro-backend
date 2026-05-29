package com.placepro.controller;

import java.util.List;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import com.placepro.dto.ApplicationRequest;
import com.placepro.entity.Application;
import com.placepro.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService) {

        this.applicationService = applicationService;
    }
    @PostMapping("/{userId}")
    public String applyJob(

            @PathVariable Long userId,

            @RequestBody ApplicationRequest request) {

        return applicationService.applyJob(
                userId,
                request
        );
    }

    @GetMapping
    public List<Application> getAllApplications() {

        return applicationService.getAllApplications();
    }
    
    @GetMapping("/my")
    public List<Application> getMyApplications(
            Authentication authentication) {

        String email = authentication.getName();

        return applicationService
                .getApplicationsByEmail(email);
    }

    @GetMapping("/recruiter")
    public List<Application> getRecruiterApplications(
            Authentication authentication) {

        String email = authentication.getName();

        return applicationService
                .getRecruiterApplications(email);
    }
    
    @PutMapping("/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return applicationService
                .updateApplicationStatus(id, status);
    }
}