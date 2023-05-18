package com.example.project_phase_2_1.components;

public abstract class Sms {
    protected final String recipient;
    protected final String greeting;
    protected final String donorName;
    protected final String message;
    protected final String appointmentDateMessage;
    protected final String appointmentDate;
    protected final String endingMessage;

    public Sms(String recipient,
               String greeting,
               String donorName,
               String message,
               String appointmentDateMessage,
               String appointmentDate,
               String endingMessage) {
        this.recipient = recipient;
        this.greeting = greeting;
        this.donorName = donorName;
        this.message = message;
        this.appointmentDateMessage = appointmentDateMessage;
        this.appointmentDate = appointmentDate;
        this.endingMessage = endingMessage;
    }

    public String getRecipient() {
        return recipient;
    }

    public abstract String getMessage();
}
