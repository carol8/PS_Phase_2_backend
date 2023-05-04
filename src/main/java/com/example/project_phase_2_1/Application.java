package com.example.project_phase_2_1;

import com.example.project_phase_2_1.components.MailBuilderRegistry;
import com.example.project_phase_2_1.service.impl.MailBuilder;
import com.example.project_phase_2_1.service.impl.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
