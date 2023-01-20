package com.exercise.travelAgency;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

    private JavaMailSender emailSender;

    public EmailServiceController(JavaMailSender emailSender) {this.emailSender = emailSender;}

    @PostMapping("/email/send")
    public ResponseEntity<?> sendSimpleEmail(@RequestBody SimpleMailMessage message) {
        EmailService aux = new EmailService(this.emailSender);
        aux.sendSimpleMessage(message);
        //methodOn(EmailService.class).sendSimpleMessage(message);
        return ResponseEntity.ok(message.getTo());
    }

}
