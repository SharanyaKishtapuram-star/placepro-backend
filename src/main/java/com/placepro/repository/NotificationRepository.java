package com.placepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placepro.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<
                Notification,
                Long> {
	List<Notification> findByStudentUserEmail(String email);

}