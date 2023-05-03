package com.example.project_phase_2_1.components;

import com.example.project_phase_2_1.service.impl.MailBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MailBuilderRegistry {
    Map<String, MailBuilder> mailBuildersMap = new HashMap<>();

    public void addMailBuilder(String id, MailBuilder mailBuilder){
        mailBuildersMap.put(id, mailBuilder);
    }

    public MailBuilder getById(String id){
        return mailBuildersMap.get(id);
    }
}
