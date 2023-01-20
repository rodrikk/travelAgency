package com.exercise.travelAgency;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

    private JavaMailSender emailSender;
    private EmailService emailService;

    public EmailServiceController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
        this.emailService = new EmailService(this.emailSender);
    }

    @PostMapping("/email/send")
    public ResponseEntity<?> sendSimpleEmail(@RequestBody SimpleMailMessage message) {
        this.emailService.sendSimpleMessage(message);
        //methodOn(EmailService.class).sendSimpleMessage(message);
        return ResponseEntity.ok(message.getTo());
    }

    @PostMapping("/email/sendMime")
    public ResponseEntity<?> sendMimeEmail(@RequestBody EmailDetails request) throws MessagingException {
        this.emailService.sendMimeMessage(request);
        return ResponseEntity.ok(request.getTo());
    }
}
