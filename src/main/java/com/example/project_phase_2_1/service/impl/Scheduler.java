package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.components.MailBuilderRegistry;
import com.example.project_phase_2_1.components.Sms;
import com.example.project_phase_2_1.components.SmsBuilderRegistry;
import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.enums.MessageType;
import com.example.project_phase_2_1.repository.AppointmentRepository;
import com.example.project_phase_2_1.service.MessageSender;
import jakarta.mail.MessagingException;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class Scheduler {
    private final AppointmentRepository appointmentRepository;
    private final MailBuilderRegistry mailBuilderRegistry;
    private final SmsBuilderRegistry smsBuilderRegistry;
    private final SenderFactory senderFactory;
    private final TaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();

    public Scheduler(AppointmentRepository appointmentRepository,
                     MailBuilderRegistry mailBuilderRegistry,
                     SmsBuilderRegistry smsBuilderRegistry,
                     SenderFactory senderFactory){
        this.appointmentRepository = appointmentRepository;
        this.mailBuilderRegistry = mailBuilderRegistry;
        this.smsBuilderRegistry = smsBuilderRegistry;
        this.senderFactory = senderFactory;
    }

    @Scheduled(cron = "0 50 5 * * *")
//    @Scheduled(fixedDelay = 30000)
    public void schedulingTest(){
        List<Appointment> appointmentList = appointmentRepository.findAllByDate(LocalDate.now().plusDays(1));
        for(Appointment appointment : appointmentList){
            if(appointment.emailNotificationsEnabled){
                asyncTaskExecutor.execute(() -> {
                    Mail mail = mailBuilderRegistry.getById(MessageType.APPOINTMENT_SOON)
                            .setRecipient(appointment.donor.email)
                            .setDonorName(appointment.donor.name)
                            .getResult(MessageType.APPOINTMENT_SOON);
                    try {
                        senderFactory.createSender(mail).send();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            if(appointment.smsNotificationsEnabled){
                asyncTaskExecutor.execute(() -> {
                    Sms sms = smsBuilderRegistry.getById(MessageType.APPOINTMENT_SOON).clone()
                            .setRecipient(appointment.donor.phone)
                            .setDonorName(appointment.donor.name)
                            .getResult(MessageType.APPOINTMENT_SOON);
                    try {
                        senderFactory.createSender(sms).send();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
