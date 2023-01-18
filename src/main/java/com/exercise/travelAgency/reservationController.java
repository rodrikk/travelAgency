package com.exercise.travelAgency;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class reservationController {

    private final reservationRepository repository;

    public reservationController(reservationRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/reservations")
    List<reservation> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/reservations")
    reservation newReservation(@RequestBody reservation newreservation) {
        return repository.save(newreservation);
    }

    // Single item

    @GetMapping("/reservations/{id}")
    reservation one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new reservationNotFoundException(id));
    }

    @PutMapping("/reservations/{id}")
    reservation replaceReservation(@RequestBody reservation newreservation, @PathVariable Integer id) {

        return repository.findById(id)
                .map(reservation -> {
                    reservation.setFirstName(newreservation.getFirstName());
                    reservation.setLastName(newreservation.getLastName());
                    reservation.setReservationDate(newreservation.getReservationDate());
                    reservation.setPaidDeposit(newreservation.getPaidDeposit());
                    return repository.save(reservation);
                })
                .orElseGet(() -> {
                    newreservation.setId(id);
                    return repository.save(newreservation);
                });
    }

    @DeleteMapping("/reservations/{id}")
    void deleteReservation(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}