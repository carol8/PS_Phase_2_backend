package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.components.MailConfiguration;
import com.example.project_phase_2_1.service.MessageSender;
import org.springframework.stereotype.Service;

@Service
public class SenderFactory {
    public MessageSender createSender(Mail mail){
        return new MailSender(mail, new MailConfiguration());
    }
//    public MessageSender createSender(Sms sms){
//        return new SmsSender(sms);
//    }
}
