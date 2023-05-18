package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.AppointmentSoonMail;
import com.example.project_phase_2_1.components.ConfirmationMail;
import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.enums.MessageType;
import com.example.project_phase_2_1.service.MessageBuilder;

public class MailBuilder implements MessageBuilder, Cloneable {

    private String recipient;
    private String subject;
    private String greeting;
    private String donorName;
    private String message;
    private String appointmentDateMessage;
    private String appointmentDate;
    private String endingMessage;


    public MailBuilder() {
    }

    public MailBuilder(MailBuilder mailBuilder) {
        if (mailBuilder.recipient != null) {
            this.recipient = mailBuilder.recipient;
        }
        if (mailBuilder.subject != null) {
            this.subject = mailBuilder.subject;
        }
        if (mailBuilder.greeting != null) {
            this.greeting = mailBuilder.greeting;
        }
        if (mailBuilder.donorName != null) {
            this.donorName = mailBuilder.donorName;
        }
        if (mailBuilder.message != null) {
            this.message = mailBuilder.message;
        }
        if (mailBuilder.appointmentDateMessage != null) {
            this.appointmentDateMessage = mailBuilder.appointmentDateMessage;
        }
        if (mailBuilder.appointmentDate != null) {
            this.appointmentDate = mailBuilder.appointmentDate;
        }
        if (mailBuilder.endingMessage != null) {
            this.endingMessage = mailBuilder.endingMessage;
        }
    }

    @Override
    public MailBuilder setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public MailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public MailBuilder setDonorName(String donorName) {
        this.donorName = donorName;
        return this;
    }

    @Override
    public MailBuilder setGreeting(String greeting) {
        this.greeting = greeting;
        return this;
    }

    @Override
    public MailBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public MailBuilder setAppointmentDateMessage(String message) {
        this.appointmentDateMessage = message;
        return this;
    }

    @Override
    public MailBuilder setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    @Override
    public MailBuilder setEndingMessage(String message) {
        this.endingMessage = message;
        return this;
    }

    @Override
    public MailBuilder clone() {
        return new MailBuilder(this);
    }

    public Mail getResult(MessageType messageType) {
        switch (messageType) {
            case CONFIRMATION -> {
                return new ConfirmationMail(recipient, subject, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
            }
            case APPOINTMENT_SOON -> {
                return new AppointmentSoonMail(recipient, subject, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
            }
        }
        return null;
    }
}
