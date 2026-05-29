package com.placepro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.placepro.dto.JobRequest;
import com.placepro.entity.Job;
import com.placepro.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {

        this.jobService = jobService;
    }

    @PostMapping
    public String createJob(
            @RequestBody JobRequest request,
            org.springframework.security.core.Authentication authentication) {

        return jobService.createJob(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();
    }
    
    @GetMapping("/my-jobs")
    public List<Job> getMyJobs(
            org.springframework.security.core.Authentication authentication) {

        return jobService.getJobsByRecruiter(
                authentication.getName()
        );
    }

    
    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody Job updatedJob) {

        return jobService.updateJob(
                id,
                updatedJob
        );
    }

    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return "Job Deleted Successfully";
        
        
    }
}