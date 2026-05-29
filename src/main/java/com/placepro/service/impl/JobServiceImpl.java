package com.placepro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placepro.dto.JobRequest;
import com.placepro.entity.Job;
import com.placepro.repository.JobRepository;
import com.placepro.service.JobService;
import com.placepro.repository.ApplicationRepository;
import org.springframework.transaction.annotation.Transactional;
import com.placepro.entity.User;
import com.placepro.repository.UserRepository;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    
    public JobServiceImpl(
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            UserRepository userRepository) {

        this.jobRepository = jobRepository;

        this.applicationRepository = applicationRepository;
        
        this.userRepository = userRepository;
    }
    @Override
    public String createJob(
            JobRequest request,
            String email) {

        User recruiter =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Recruiter Not Found"
                        ));

        Job job = new Job();

        job.setCompanyName(request.getCompanyName());
        job.setJobRole(request.getJobRole());
        job.setJobDescription(request.getJobDescription());
        job.setLocation(request.getLocation());
        job.setSalaryPackage(request.getSalaryPackage());

        job.setRecruiter(recruiter);

        jobRepository.save(job);

        return "Job Created Successfully";
    }

    @Override
    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }
    
    @Override
    public List<Job> getJobsByRecruiter(String email) {

        User recruiter =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Recruiter Not Found"
                        ));

        return jobRepository.findByRecruiter(
                recruiter
        );
    }

    @Override
    public Job updateJob(
            Long id,
            Job updatedJob) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job Not Found"));

        job.setCompanyName(
                updatedJob.getCompanyName());

        job.setJobRole(
                updatedJob.getJobRole());

        job.setLocation(
                updatedJob.getLocation());

        job.setSalaryPackage(
                updatedJob.getSalaryPackage());

        return jobRepository.save(job);
    }



    @Override
    @Transactional
    public String deleteJob(Long id) {

        applicationRepository.deleteByJobId(id);

        jobRepository.deleteById(id);

        return "Job Deleted Successfully";
    }
}