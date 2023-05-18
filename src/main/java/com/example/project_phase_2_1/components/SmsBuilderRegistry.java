package com.example.project_phase_2_1.components;

import com.example.project_phase_2_1.enums.MessageType;
import com.example.project_phase_2_1.service.impl.SmsBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SmsBuilderRegistry {
    private final Map<MessageType, SmsBuilder> smsBuilderMap = new HashMap<>();

    public SmsBuilderRegistry() {
        addSmsBuilder(MessageType.CONFIRMATION, new SmsBuilder()
                .setSubject("Confirmare programare donatie de sange")
                .setGreeting("Buna ")
                .setMessage("Te-ai programat cu succes la donat de sange.")
                .setAppointmentDateMessage("Data programarii: ")
                .setEndingMessage("Cu stima,\nEchipa ps_bloodbank"));
        addSmsBuilder(MessageType.APPOINTMENT_SOON, new SmsBuilder()
                .setSubject("Reminder Programare donatie de sange")
                .setGreeting("Buna ")
                .setMessage("Sunteti programat(a) la donat de sange maine. Ne vedem acolo!")
                .setEndingMessage("Cu stima,\nEchipa ps_bloodbank"));
    }

    private void addSmsBuilder(MessageType messageType, SmsBuilder smsBuilder) {
        smsBuilderMap.put(messageType, smsBuilder);
    }

    public SmsBuilder getById(MessageType messageType) {
        return smsBuilderMap.get(messageType);
    }
}
