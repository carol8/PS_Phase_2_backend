package com.example.project_phase_2_1.components;

public class Mail { //TODO oare e bine cum e facut? In ce pachet ar trebui sa fie?
    private final String recipient;
    private final String subject;
    private final String greeting;
    private final String donorName;
    private final String message;
    private final String appointmentDateMessage;
    private final String appointmentDate;

    public Mail(String recipient, String subject, String greeting, String donorName, String message, String appointmentDateMessage, String appointmentDate) {
        this.recipient = recipient;
        this.subject = subject;
        this.greeting = greeting;
        this.donorName = donorName;
        this.message = message;
        this.appointmentDateMessage = appointmentDateMessage;
        this.appointmentDate = appointmentDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getMessage() {
        return message;
    }

    public String getAppointmentDateMessage() {
        return appointmentDateMessage;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}
