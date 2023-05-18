package com.example.project_phase_2_1.components;

import com.example.project_phase_2_1.enums.MessageType;
import com.example.project_phase_2_1.service.impl.MailBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MailBuilderRegistry {
    private final Map<MessageType, MailBuilder> mailBuildersMap = new HashMap<>();

    public MailBuilderRegistry() {
        addMailBuilder(MessageType.CONFIRMATION, new MailBuilder()
                .setSubject("Confirmare programare donatie de sange")
                .setGreeting("Buna ")
                .setMessage("Te-ai programat cu succes la donat de sange.")
                .setAppointmentDateMessage("Data programarii: ")
                .setEndingMessage("Cu stima,\nEchipa ps_bloodbank"));
        addMailBuilder(MessageType.APPOINTMENT_SOON, new MailBuilder()
                .setSubject("Reminder Programare donatie de sange")
                .setGreeting("Buna ")
                .setMessage("Sunteti programat(a) la donat de sange maine. Ne vedem acolo!")
                .setEndingMessage("Cu stima,\nEchipa ps_bloodbank"));
    }

    private void addMailBuilder(MessageType messageType, MailBuilder mailBuilder) {
        mailBuildersMap.put(messageType, mailBuilder);
    }

    public MailBuilder getById(MessageType messageType) {
        return mailBuildersMap.get(messageType);
    }
}
