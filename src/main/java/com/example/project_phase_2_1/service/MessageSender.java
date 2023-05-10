package com.example.project_phase_2_1.service;

import jakarta.mail.MessagingException;

public interface MessageSender {
    void send() throws MessagingException;
}
