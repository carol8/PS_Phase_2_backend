package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Sms;
import com.example.project_phase_2_1.service.MessageSender;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsSender implements MessageSender {

    private final Sms sms;

    private final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public SmsSender(Sms sms) {
        this.sms = sms;
    }

    @Override
    public void send() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(sms.getRecipient()),
                        new com.twilio.type.PhoneNumber("+13204337161"),
                        sms.getMessage())
                .create();
    }
}
