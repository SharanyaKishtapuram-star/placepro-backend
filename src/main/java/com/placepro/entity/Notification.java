package com.placepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(
            strategy =
            GenerationType.IDENTITY
    )
    private Long id;

    private String message;

    private boolean isRead = false;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
            String message) {

        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(
            boolean read) {

        isRead = read;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(
            Student student) {

        this.student = student;
    }
}