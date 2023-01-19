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
class reservationController {

    private final reservationRepository repository;
    private final reservationModelAssembler assembler;

    private final travelPkgController packCont;

    public reservationController(reservationRepository repository, reservationModelAssembler assemble, travelPkgController packCon) {
        this.repository = repository;
        this.assembler = assemble;
        this.packCont = packCon;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/reservations")
    CollectionModel<EntityModel<reservation>> all() {

        List<EntityModel<reservation>> reserves = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reserves, linkTo(methodOn(reservationController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/reservations")
    reservation newReservation(@RequestBody reservation newreservation) {
        return repository.save(newreservation);
    }

    // Single item

    @GetMapping("/reservations/{id}")
    EntityModel<reservation> one(@PathVariable Integer id) {

        reservation res = repository.findById(id)
                .orElseThrow(() -> new reservationNotFoundException(id));

        return assembler.toModel(res);
    }

    @GetMapping("/reservations/{id}/bookedPkg")
    EntityModel<travelPkg> getsPack(@PathVariable Integer id) {
        return packCont.one(repository.findById(id)
                .orElseThrow(() -> new reservationNotFoundException(id)).getBookedPkg().getId());
    }

    @PutMapping("/reservations/{id}")
    reservation replaceReservation(@RequestBody reservation newreservation, @PathVariable Integer id) {

        return repository.findById(id)
                .map(reservation -> {
                    reservation.setFirstName(newreservation.getFirstName());
                    reservation.setLastName(newreservation.getLastName());
                    reservation.setReservationDate(newreservation.getReservationDate());
                    reservation.setPaidDeposit(newreservation.getPaidDeposit());
                    reservation.setBookedPkg(newreservation.getBookedPkg());
                    return repository.save(reservation);
                })
                .orElseGet(() -> {
                    newreservation.setId(id);
                    return repository.save(newreservation);
                });
    }

    @PutMapping("/reservations/{id}/bookedPkg")
    reservation replaceBookedPkg(@RequestBody String packLink, @PathVariable Integer id) {
        String[] aux = packLink.split("/");
        travelPkg pack = packCont.one(Integer.parseInt(aux[aux.length-1])).getContent();
        return repository.findById(id)
                .map(reservation -> {
                    reservation.setBookedPkg(pack);
                    return repository.save(reservation);
                })
                .orElseThrow(() -> new reservationNotFoundException(id));
    }

    @DeleteMapping("/reservations/{id}")
    void deleteReservation(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}