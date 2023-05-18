package com.example.project_phase_2_1.components;

public abstract class Mail extends BbMessage {
    protected final String subject;

    public Mail(String recipient,
                String subject,
                String greeting,
                String donorName,
                String message,
                String appointmentDateMessage,
                String appointmentDate,
                String endingMessage) {
        super(recipient, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
        this.subject = subject;
    }

    @Override
    public String getSubject() {
        return subject;
    }
}
