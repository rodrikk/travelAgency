package com.exercise.travelAgency;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
@Component
public class reservationModelAssembler implements RepresentationModelAssembler<reservation, EntityModel<reservation>>{

    @Override
    public EntityModel<reservation> toModel(reservation entity) {
        try {
            return EntityModel.of(entity, //
                    linkTo(methodOn(reservationController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(reservationController.class).all()).withRel("reservations"),
                    linkTo(methodOn(travelPkgController.class).one(entity.getBookedPkg().getId())).withRel("travelPkgs"));
        } catch (NullPointerException ex) {
            return EntityModel.of(entity, //
                    linkTo(methodOn(reservationController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(reservationController.class).all()).withRel("reservations"));
        }
    }
}
