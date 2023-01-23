package com.exercise.travelAgency;

import jakarta.mail.MessagingException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmailServiceController {

    private EmailDetailsRepository repository;
    private EmailDetailsModelAssembler assembler;
    private JavaMailSender emailSender;
    private EmailService emailService;

    public EmailServiceController(EmailDetailsRepository repo, EmailDetailsModelAssembler assemble, JavaMailSender emailSender) {
        this.repository = repo;
        this.assembler = assemble;
        this.emailSender = emailSender;
        this.emailService = new EmailService(this.emailSender);
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/emails")
    CollectionModel<EntityModel<EmailDetails>> all() {

        List<EntityModel<EmailDetails>> emails = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(emails, linkTo(methodOn(EmailServiceController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @GetMapping("/emails/{id}")
    EntityModel<EmailDetails> one(@PathVariable Integer id) {

        EmailDetails email = repository.findById(id)
                .orElseThrow(() -> new EmailDetailsNotFoundException(id));

        return assembler.toModel(email);
    }

    @DeleteMapping("/emails/{id}")
    void deleteEmail(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PostMapping("/email/send")
    public EmailDetails sendMimeEmail(@RequestBody EmailDetails request) throws MessagingException {
        this.emailService.sendMimeMessage(request);
        return repository.save(request);
    }
}
