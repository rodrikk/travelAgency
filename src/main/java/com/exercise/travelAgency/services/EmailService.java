package com.exercise.travelAgency.services;

import com.exercise.travelAgency.models.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailService {

    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {this.emailSender=emailSender;}

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
