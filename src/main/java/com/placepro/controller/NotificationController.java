package com.placepro.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placepro.entity.Notification;
import com.placepro.repository.NotificationRepository;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public List<Notification> getNotifications(
            Authentication authentication) {

        String email = authentication.getName();

        return notificationRepository
                .findByStudentUserEmail(email);
    }
}