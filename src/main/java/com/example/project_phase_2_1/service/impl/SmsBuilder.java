package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.AppointmentSoonSms;
import com.example.project_phase_2_1.components.ConfirmationSms;
import com.example.project_phase_2_1.components.Sms;
import com.example.project_phase_2_1.enums.MessageType;
import com.example.project_phase_2_1.service.MessageBuilder;

public class SmsBuilder implements MessageBuilder, Cloneable {
    private String recipient;
    private String greeting;
    private String donorName;
    private String message;
    private String appointmentDateMessage;
    private String appointmentDate;
    private String endingMessage;

    public SmsBuilder() {

    }

    public SmsBuilder(SmsBuilder smsBuilder) {
        if (smsBuilder.recipient != null) {
            this.recipient = smsBuilder.recipient;
        }
        if (smsBuilder.greeting != null) {
            this.greeting = smsBuilder.greeting;
        }
        if (smsBuilder.donorName != null) {
            this.donorName = smsBuilder.donorName;
        }
        if (smsBuilder.message != null) {
            this.message = smsBuilder.message;
        }
        if (smsBuilder.appointmentDateMessage != null) {
            this.appointmentDateMessage = smsBuilder.appointmentDateMessage;
        }
        if (smsBuilder.appointmentDate != null) {
            this.appointmentDate = smsBuilder.appointmentDate;
        }
        if (smsBuilder.endingMessage != null) {
            this.endingMessage = smsBuilder.endingMessage;
        }
    }

    @Override
    public SmsBuilder setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public SmsBuilder setSubject(String subject) {
        return this;
    }

    @Override
    public SmsBuilder setDonorName(String donorName) {
        this.donorName = donorName;
        return this;
    }

    @Override
    public SmsBuilder setGreeting(String greeting) {
        this.greeting = greeting;
        return this;
    }

    @Override
    public SmsBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public SmsBuilder setAppointmentDateMessage(String message) {
        this.appointmentDateMessage = message;
        return this;
    }

    @Override
    public SmsBuilder setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    @Override
    public SmsBuilder setEndingMessage(String message) {
        this.endingMessage = message;
        return this;
    }

    @Override
    public SmsBuilder clone() {
        return new SmsBuilder(this);
    }

    public Sms getResult(MessageType messageType) {
        switch (messageType) {
            case CONFIRMATION -> {
                return new ConfirmationSms(recipient, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
            }
            case APPOINTMENT_SOON -> {
                return new AppointmentSoonSms(recipient, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
            }
        }
        return null;
    }
}
