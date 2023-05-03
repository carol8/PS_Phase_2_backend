package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.service.MessageSender;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailSender implements MessageSender {

    private final Mail mail;

    public MailSender(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void send() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", 2525);
        properties.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.connectiontimeout", "10000");
        properties.put("mail.smtp.timeout", "10000");

        Session session = Session.getInstance(properties);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("bloodbank@psbloodbank.org"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipient()));
        message.setSubject(mail.getSubject());

        String mailMessage = mail.getGreeting() + mail.getDonorName()
                + ",\n\n"
                + mail.getMessage() +
                "\n" +
                mail.getAppointmentDateMessage() + mail.getAppointmentDate();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(mailMessage, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message, "ed5645b7cbe0e2", "b9627c1ff8112e");
    }
}
