package com.exercise.travelAgency.controllers;

import com.exercise.travelAgency.exceptions.emailDetailsNotFoundException;
import com.exercise.travelAgency.modelAssemblers.emailDetailsModelAssembler;
import com.exercise.travelAgency.models.emailDetails;
import com.exercise.travelAgency.repositories.emailDetailsRepository;
import com.exercise.travelAgency.services.emailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class emailDetailsController {

    @Autowired
    private emailDetailsRepository repository;
    @Autowired
    private emailDetailsModelAssembler assembler;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private com.exercise.travelAgency.services.emailService emailService;

    public emailDetailsController() {
        this.emailService = new emailService(this.emailSender);
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/emails")
    public CollectionModel<EntityModel<emailDetails>> all() {

        List<EntityModel<emailDetails>> emails = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(emails, linkTo(methodOn(emailDetailsController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @GetMapping("/emails/{id}")
    public EntityModel<emailDetails> one(@PathVariable Integer id) {

        emailDetails email = repository.findById(id)
                .orElseThrow(() -> new emailDetailsNotFoundException(id));

        return assembler.toModel(email);
    }

    @DeleteMapping("/emails/{id}")
    void deleteEmail(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    //Method that handles the input of the Email Details of the message that the user wants to send.
    @PostMapping("/email/send")
    public emailDetails sendMimeEmail(@RequestBody emailDetails request) throws MessagingException {
        this.emailService.sendMimeMessage(request);
        return repository.save(request);
    }
}
