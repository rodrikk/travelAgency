package com.exercise.travelAgency.controllers;

import com.exercise.travelAgency.exceptions.travelPkgNotFoundException;
import com.exercise.travelAgency.modelAssemblers.travelPkgModelAssembler;
import com.exercise.travelAgency.models.travelPkg;
import com.exercise.travelAgency.repositories.travelPkgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class travelPkgController {
    @Autowired
    private travelPkgRepository repository;
    @Autowired
    private travelPkgModelAssembler assembler;

    public travelPkgController() {
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/travelPkgs")
    public CollectionModel<EntityModel<travelPkg>> all() {

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
    public EntityModel<travelPkg> one(@PathVariable Integer id) {

        travelPkg pack = repository.findById(id)
                .orElseThrow(() -> new travelPkgNotFoundException(id));

        return assembler.toModel(pack);
    }

    @GetMapping("/travelPkgsByDepartDate")
    public List<travelPkg> findByDepartDate(@RequestBody String stringDepartDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return repository.findByDepartDate(dateFormat.parse(stringDepartDate));
    }

    @GetMapping("/travelPkgsByDateInterval")
    public List<travelPkg> findByDateInterval(@RequestBody Map<String, String> request) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return repository.findByDateInterval(dateFormat.parse(request.get("stringDepartDate")), dateFormat.parse(request.get("stringReturnDate")));
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