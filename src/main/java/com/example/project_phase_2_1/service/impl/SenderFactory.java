package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.components.Sms;
import com.example.project_phase_2_1.service.MessageSender;
import org.springframework.stereotype.Service;

@Service
public class SenderFactory {
    public MessageSender createMailSender() {
        return new MailSender();
    }

    public MessageSender createSmsSender() {
        return new SmsSender();
    }
}
