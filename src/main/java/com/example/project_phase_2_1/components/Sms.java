package com.example.project_phase_2_1.components;

public abstract class Sms extends BbMessage {
    public Sms(String recipient,
               String greeting,
               String donorName,
               String message,
               String appointmentDateMessage,
               String appointmentDate,
               String endingMessage) {
        super(recipient, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
    }
}
