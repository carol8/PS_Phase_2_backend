package com.example.project_phase_2_1.service;

public interface MessageBuilder {
    MessageBuilder setRecipient(String recipient);

    MessageBuilder setSubject(String subject);

    MessageBuilder setGreeting(String greeting);

    MessageBuilder setDonorName(String donorName);

    MessageBuilder setMessage(String message);

    MessageBuilder setAppointmentDateMessage(String message);

    MessageBuilder setAppointmentDate(String appointmentDate);

    MessageBuilder setEndingMessage(String message);
}
