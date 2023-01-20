package com.exercise.travelAgency;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

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

    public void sendMimeMessage(EmailDetails details) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(details.getFrom());
        helper.setTo(details.getTo());
        helper.setSubject(details.getSubject());
        helper.setText(details.getText());

        if(details.isHasCC())
            helper.setCc(details.getCc());

        FileSystemResource file
                = new FileSystemResource(new File(details.getPathToAttachment()));
        helper.addAttachment(details.getAttachmentName(), file);

        emailSender.send(message);

    }
}
