package com.example.project_phase_2_1.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailConfiguration {
//    @Value("${spring.mail.host}")
    private String host = "sandbox.smtp.mailtrap.io";
//    @Value("${spring.mail.port}")
    private Integer port = 2525;
//    @Value("${spring.mail.username}")
    private String username = "ed5645b7cbe0e2";
//    @Value("${spring.mail.password}")
    private String password = "b9627c1ff8112e";

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
