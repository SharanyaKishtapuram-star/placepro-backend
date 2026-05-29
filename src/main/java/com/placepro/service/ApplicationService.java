package com.placepro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.placepro.dto.ApplicationRequest;
import com.placepro.entity.Application;
import com.placepro.entity.Job;
import com.placepro.entity.Student;
import com.placepro.entity.User;
import com.placepro.entity.Notification;
import com.placepro.repository.ApplicationRepository;
import com.placepro.repository.JobRepository;
import com.placepro.repository.StudentRepository;
import com.placepro.repository.UserRepository;
import com.placepro.repository.NotificationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final StudentRepository studentRepository;

    private final JobRepository jobRepository;

    private final UserRepository userRepository;
    
    private final NotificationRepository notificationRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository,
            UserRepository userRepository,
            NotificationRepository notificationRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    public String applyJob(
            Long userId,
            ApplicationRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        boolean alreadyApplied =
                applicationRepository
                .existsByStudentIdAndJobId(
                        student.getId(),
                        request.getJobId()
                );

        if (alreadyApplied) {

            return "Already Applied";
        }

        Job job =
                jobRepository.findById(
                        request.getJobId()
                ).orElseThrow(() ->
                        new RuntimeException("Job Not Found"));

        Application application = new Application();

        application.setStudent(student);

        application.setJob(job);

        application.setStatus("APPLIED");

        applicationRepository.save(application);

        return "Application Submitted Successfully";
    }

    public List<Application> getAllApplications() {
    	
        return applicationRepository.findAll();
    }
    public List<Application> getApplicationsByEmail(
            String email) {

        return applicationRepository
                .findByStudentUserEmail(email);
    }

    public String updateApplicationStatus(
            Long applicationId,
            String status) {

        Application application =
                applicationRepository.findById(
                        applicationId
                ).orElseThrow();

        application.setStatus(status);

        applicationRepository.save(application);
        
        Notification notification =
                new Notification();

        notification.setStudent(
                application.getStudent()
        );

        notification.setMessage(

                "Your application for "

                +

                application.getJob()
                .getCompanyName()

                +

                " has been "

                +

                status
        );

        notificationRepository
                .save(notification);

        return "Application Status Updated";
    }
    public List<Application> getRecruiterApplications(
            String email) {

        User recruiter = userRepository
                .findByEmail(email)
                .orElseThrow();

        return applicationRepository.findAll()
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
                .toList();
    } 
}
