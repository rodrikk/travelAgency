package com.exercise.travelAgency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {this.emailSender=emailSender;}

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rbauhern@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendSimpleMessage(SimpleMailMessage message) {
        emailSender.send(message);
    }
}
