package com.exercise.travelAgency.modelAssemblers;

import com.exercise.travelAgency.controllers.reservationController;
import com.exercise.travelAgency.controllers.travelPkgController;
import com.exercise.travelAgency.models.Status;
import com.exercise.travelAgency.models.reservation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

        if(entity.getStatus()== Status.IN_PROGRESS) {
            modelo.add(linkTo(methodOn(reservationController.class).cancel(entity.getId())).withRel("Cancel"));
            modelo.add(linkTo(methodOn(reservationController.class).complete(entity.getId())).withRel("Complete"));
        }
        else if(entity.getStatus() == Status.COMPLETED)
            modelo.add(linkTo(methodOn(reservationController.class).cancel(entity.getId())).withRel("Cancel"));

        return modelo;
    }
}
