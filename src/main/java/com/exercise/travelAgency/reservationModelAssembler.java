package com.exercise.travelAgency;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
@Component
public class reservationModelAssembler implements RepresentationModelAssembler<reservation, EntityModel<reservation>>{

    @Override
    public EntityModel<reservation> toModel(reservation entity) {
        EntityModel<reservation> modelo;
        try {
             modelo = EntityModel.of(entity, //
                    linkTo(methodOn(reservationController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(reservationController.class).all()).withRel("reservations"),
                    linkTo(methodOn(travelPkgController.class).one(entity.getBookedPkg().getId())).withRel("bookedPkg"));
        } catch (NullPointerException ex) {
            modelo = EntityModel.of(entity, //
                    linkTo(methodOn(reservationController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(reservationController.class).all()).withRel("reservations"));
        }

        if(entity.getStatus()==Status.IN_PROGRESS) {
            modelo.add(linkTo(methodOn(reservationController.class).cancel(entity.getId())).withRel("Cancel"));
            modelo.add(linkTo(methodOn(reservationController.class).complete(entity.getId())).withRel("Complete"));
        }
        return modelo;
    }
}
