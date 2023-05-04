package com.example.project_phase_2_1.components;

public class ConfirmationSms extends Sms{
    public ConfirmationSms(String recipient, String greeting, String donorName, String message, String appointmentDateMessage, String appointmentDate, String endingMessage) {
        super(recipient, greeting, donorName, message, appointmentDateMessage, appointmentDate, endingMessage);
    }

    @Override
    public String getMessage() {
        return null;
    }
}
