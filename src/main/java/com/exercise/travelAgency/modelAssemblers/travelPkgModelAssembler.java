package com.exercise.travelAgency.modelAssemblers;

import com.exercise.travelAgency.controllers.travelPkgController;
import com.exercise.travelAgency.models.travelPkg;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class travelPkgModelAssembler implements RepresentationModelAssembler<travelPkg, EntityModel<travelPkg>>{

    @Override
    public EntityModel<travelPkg> toModel(travelPkg entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(travelPkgController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(travelPkgController.class).all()).withRel("travelPkgs"));
    }
}
