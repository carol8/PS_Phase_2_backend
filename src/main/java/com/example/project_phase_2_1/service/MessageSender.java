package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.components.BbMessage;
import jakarta.mail.MessagingException;

public interface MessageSender {
    void send(BbMessage bbMessage) throws MessagingException;
}
