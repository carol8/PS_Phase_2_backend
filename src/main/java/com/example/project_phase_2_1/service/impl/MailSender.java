package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.components.MailConfiguration;
import com.example.project_phase_2_1.service.MessageSender;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailSender implements MessageSender {

    private final Mail mail;
    private final MailConfiguration mailConfiguration;

    public MailSender(Mail mail, MailConfiguration mailConfiguration) {
        this.mail = mail;
        this.mailConfiguration = mailConfiguration;
    }

    @Override
    public void send() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host",  mailConfiguration.getHost());
        properties.put("mail.smtp.port", mailConfiguration.getPort());
        properties.put("mail.smtp.ssl.trust", mailConfiguration.getHost());
        properties.put("mail.smtp.connectiontimeout", "2000");
        properties.put("mail.smtp.timeout", "2000");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfiguration.getUsername(), mailConfiguration.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("6969@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipient()));
        message.setSubject(mail.getSubject());

        String mailMessage = mail.getGreeting() + ", " + mail.getDonorName() + ",\n\n"
                + mail.getMessage() +
                "\n" +
                mail.getAppointmentDateMessage() + mail.getAppointmentDate();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(mailMessage, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        System.out.println("Sending mail");
        Transport.send(message);
        System.out.println("Mail sent");
    }
}
