package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.service.MessageBuilder;

public class MailBuilder implements MessageBuilder, Cloneable {

    private String recipient;
    private String subject;
    private String greeting;
    private String donorName;
    private String message;
    private String appointmentDateMessage;
    private String appointmentDate;


    public MailBuilder() {
    }

    public MailBuilder(MailBuilder mailBuilder) {
        if(recipient != null){
            this.recipient = mailBuilder.recipient;
        }
        if(subject != null){
            this.subject = mailBuilder.subject;
        }
        if(greeting != null){
            this.greeting = mailBuilder.greeting;
        }
        if(donorName != null){
            this.donorName = mailBuilder.donorName;
        }
        if(message != null){
            this.message = mailBuilder.message;
        }
        if(appointmentDateMessage != null){
            this.appointmentDateMessage = mailBuilder.appointmentDateMessage;
        }
        if(appointmentDate != null){
            this.appointmentDate = mailBuilder.appointmentDate;
        }
    }

    @Override
    public MailBuilder setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public MailBuilder setSubject(String subject){
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
    public MailBuilder setAppointmentDateMessage(String message){
        this.appointmentDateMessage = message;
        return this;
    }

    @Override
    public MailBuilder setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    @Override
    public MailBuilder clone() {
        return new MailBuilder(this);
    }

    public Mail getResult(){
        return new Mail(recipient, subject, greeting, donorName, message, appointmentDateMessage, appointmentDate);
    }
}