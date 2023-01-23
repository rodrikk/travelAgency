package com.exercise.travelAgency;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
@Component
public class EmailDetailsModelAssembler implements RepresentationModelAssembler<EmailDetails, EntityModel<EmailDetails>> {
    @Override
    public EntityModel<EmailDetails> toModel(EmailDetails entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(EmailDetailsController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(EmailDetailsController.class).all()).withRel("emails"));
    }
}

