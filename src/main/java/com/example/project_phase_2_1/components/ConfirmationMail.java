package com.example.project_phase_2_1.components;

public class ConfirmationMail extends Mail{
    public ConfirmationMail(String recipient,
                            String subject,
                            String greeting,
                            String donorName,
                            String message,
                            String appointmentDateMessage,
                            String appointmentDate,
                            String endingMessage) {
        super(recipient, subject, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
    }

    @Override
    public String getMessage() {
        return (greeting == null ? "" : greeting) +
                (donorName == null ? "" : donorName) +
                ",\n\n" +
                (message == null ? "" : message) +
                "\n" +
                (appointmentDateMessage == null ? "" : appointmentDateMessage) +
                (appointmentDate == null ? "" : appointmentDate) +
                "\n\n" +
                (endingMessage == null ? "" : endingMessage);
    }
}
