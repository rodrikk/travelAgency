package com.exercise.travelAgency;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class travelPkgController {

    private final travelPkgRepository repository;
    private final travelPkgModelAssembler assembler;

    public travelPkgController(travelPkgRepository repository, travelPkgModelAssembler assemble) {
        this.repository = repository;
        this.assembler = assemble;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/travelPkgs")
    CollectionModel<EntityModel<travelPkg>> all() {

        List<EntityModel<travelPkg>> packs = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(packs, linkTo(methodOn(reservationController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/travelPkgs")
    travelPkg newTravelPkg(@RequestBody travelPkg newtravelPkg) {
        return repository.save(newtravelPkg);
    }

    // Single item

    @GetMapping("/travelPkgs/{id}")
    EntityModel<travelPkg> one(@PathVariable Integer id) {

        travelPkg pack = repository.findById(id)
                .orElseThrow(() -> new travelPkgNotFoundException(id));

        return assembler.toModel(pack);
    }

    @PutMapping("/travelPkgs/{id}")
    travelPkg replaceTravelPkg(@RequestBody travelPkg newtravelPkg, @PathVariable Integer id) {

        return repository.findById(id)
                .map(travelPkg -> {
                    travelPkg.setAirLineName(newtravelPkg.getAirLineName());
                    travelPkg.setHotelName(newtravelPkg.getHotelName());
                    travelPkg.setDestination(newtravelPkg.getDestination());
                    travelPkg.setHotelService(newtravelPkg.getHotelService());
                    travelPkg.setPricePerPerson(newtravelPkg.getPricePerPerson());
                    travelPkg.setReturnDate(newtravelPkg.getReturnDate());
                    travelPkg.setDepartDate(newtravelPkg.getDepartDate());
                    travelPkg.setReturnFlight(newtravelPkg.getReturnFlight());
                    return repository.save(travelPkg);
                })
                .orElseGet(() -> {
                    newtravelPkg.setId(id);
                    return repository.save(newtravelPkg);
                });
    }

    @DeleteMapping("/travelPkgs/{id}")
    void deleteTravelPkg(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}