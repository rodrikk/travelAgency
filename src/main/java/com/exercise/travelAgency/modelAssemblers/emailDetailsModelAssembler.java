package com.exercise.travelAgency.modelAssemblers;

import com.exercise.travelAgency.controllers.emailDetailsController;
import com.exercise.travelAgency.models.emailDetails;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class emailDetailsModelAssembler implements RepresentationModelAssembler<emailDetails, EntityModel<emailDetails>> {
    @Override
    public EntityModel<emailDetails> toModel(emailDetails entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(emailDetailsController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(emailDetailsController.class).all()).withRel("emails"));
    }
}

