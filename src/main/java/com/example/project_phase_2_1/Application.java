package com.example.project_phase_2_1;

import com.example.project_phase_2_1.components.MailBuilderRegistry;
import com.example.project_phase_2_1.service.impl.MailBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
