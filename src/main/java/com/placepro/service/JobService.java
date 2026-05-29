package com.placepro.service;

import java.util.List;

import com.placepro.dto.JobRequest;
import com.placepro.entity.Job;

public interface JobService {

	String createJob(
	        JobRequest request,
	        String email
	);

    List<Job> getAllJobs();
    
    List<Job> getJobsByRecruiter(String email);

    String deleteJob(Long id);

    Job updateJob(
            Long id,
            Job updatedJob
    );
}