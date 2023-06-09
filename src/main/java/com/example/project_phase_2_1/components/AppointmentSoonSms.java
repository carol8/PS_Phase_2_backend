package com.example.project_phase_2_1.components;

public class AppointmentSoonSms extends Sms {
    public AppointmentSoonSms(String recipient, String greeting, String donorName, String message, String appointmentDateMessage, String appointmentDate, String endingMessage) {
        super(recipient, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
    }

    @Override
    public String getMessage() {
        return "\n\n" +
                (greeting == null ? "" : greeting) +
                (donorName == null ? "" : donorName) +
                ",\n\n" +
                (message == null ? "" : message) +
                "\n" +
                (appointmentDateMessage == null ? "" : appointmentDateMessage) +
                (appointmentDate == null ? "" : appointmentDate) +
                "\n\n" +
                (endingMessage == null ? "" : endingMessage);
    }

    @Override
    public String getSubject() {
        return null;
    }
}
