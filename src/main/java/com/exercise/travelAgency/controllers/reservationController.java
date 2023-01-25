package com.exercise.travelAgency.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.exercise.travelAgency.DTOs.reservationDTO;
import com.exercise.travelAgency.exceptions.reservationNotFoundException;
import com.exercise.travelAgency.modelAssemblers.reservationModelAssembler;
import com.exercise.travelAgency.models.Status;
import com.exercise.travelAgency.models.reservation;
import com.exercise.travelAgency.models.travelPkg;
import com.exercise.travelAgency.repositories.reservationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public
class reservationController {

    private final reservationRepository repository;
    private final reservationModelAssembler assembler;
    private final TypeMap<reservation, reservationDTO> mapper;
    private final travelPkgController packCont;

    public reservationController(reservationRepository repository, reservationModelAssembler assemble, travelPkgController packCon) {
        this.repository = repository;
        this.assembler = assemble;
        this.packCont = packCon;
        //Type mapper generated to map the Reservation entity to the reservationDTOs
        ModelMapper aux = new ModelMapper();
        this.mapper = aux.createTypeMap(reservation.class, reservationDTO.class);
        this.mapperSetup();
    }

    //Function used to set the mapper up so that it also maps the data of the Booked package associated with the reservation.
    private void mapperSetup() {
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getAirLineName() , reservationDTO::setAirLineName);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getDestination() , reservationDTO::setDestination);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getDepartDate() , reservationDTO::setDepartDate);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getHotelName() , reservationDTO::setHotelName);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getHotelService() , reservationDTO::setHotelService);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getReturnDate() , reservationDTO::setReturnDate);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getReturnFlight() , reservationDTO::setReturnFlight);
        this.mapper.addMapping(reservation -> reservation.getBookedPkg().getPricePerPerson() , reservationDTO::setPricePerPerson);
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/reservations")
    public CollectionModel<EntityModel<reservation>> all() {

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
    public EntityModel<reservation> one(@PathVariable Integer id) {

        reservation res = repository.findById(id)
                .orElseThrow(() -> new reservationNotFoundException(id));

        return assembler.toModel(res);
    }

    @GetMapping("/reservationsByName/{firstName}/{lastName}")
    public CollectionModel<EntityModel<reservation>> findByClientName(@PathVariable String firstName, @PathVariable String lastName) {
        List<EntityModel<reservation>> reserves = repository.findAll().stream()
                .filter(element -> element.getFirstName().equals(firstName)&&element.getLastName().equals(lastName))
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reserves, linkTo(methodOn(reservationController.class).all()).withSelfRel());
    }

    @GetMapping("/reservationDTO/{id}")
    public reservationDTO getOneDTO(@PathVariable Integer id) {
        return this.convertToDTO(repository.findById(id)
                .orElseThrow(() -> new reservationNotFoundException(id)));
    }

    private reservationDTO convertToDTO(reservation reserve) {
        return this.mapper.map(reserve);
    }

    //Function that handles GET requests for the Travel Package associated with a reservation.
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
                    reservation.setStatus(newreservation.getStatus());
                    return repository.save(reservation);
                })
                .orElseGet(() -> {
                    newreservation.setId(id);
                    return repository.save(newreservation);
                });
    }

    //Method that handles the update of the Travel package associated with a Reservation.
    @PutMapping("/reservations/{id}/bookedPkg")
    reservation replaceBookedPkg(@RequestBody String packLink, @PathVariable Integer id) {
        org.springframework.web.util.UriTemplate uriTemplate = new org.springframework.web.util.UriTemplate("/travelPkgs/{packId}");
        Map<String, String> uriVars = uriTemplate.match(packLink);
        travelPkg pack = packCont.one(Integer.parseInt(uriVars.get("packId"))).getContent();
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

    //Method that can be used to set the Status of a Reservation to Cancelled over HTTP
    @DeleteMapping("/reservations/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Integer id) {

        reservation reserve = repository.findById(id) //
                .orElseThrow(() -> new reservationNotFoundException(id));

        if (reserve.getStatus() == Status.IN_PROGRESS || reserve.getStatus() == Status.COMPLETED) {
            reserve.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(repository.save(reserve)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't cancel an order that is in the " + reserve.getStatus() + " status"));
    }

    //Method that can be used to set the Status of a Reservation to Completed over HTTP
    @PutMapping("/reservations/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Integer id) {

        reservation reserve = repository.findById(id) //
                .orElseThrow(() -> new reservationNotFoundException(id));

        if (reserve.getStatus() == Status.IN_PROGRESS) {
            reserve.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(repository.save(reserve)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't complete an order that is in the " + reserve.getStatus() + " status"));
    }
}