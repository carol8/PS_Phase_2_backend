package com.example.project_phase_2_1.components;

public abstract class Mail { //TODO oare e bine cum e facut? In ce pachet ar trebui sa fie?
    protected final String recipient;
    protected final String subject;
    protected final String greeting;
    protected final String donorName;
    protected final String message;
    protected final String appointmentDateMessage;
    protected final String appointmentDate;

    protected final String endingMessage;

    public Mail(String recipient,
                String subject,
                String greeting,
                String donorName,
                String message,
                String appointmentDateMessage,
                String appointmentDate,
                String endingMessage) {
        this.recipient = recipient;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    public abstract String getMessage();
}
