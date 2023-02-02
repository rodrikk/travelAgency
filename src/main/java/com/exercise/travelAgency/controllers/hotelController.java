package com.exercise.travelAgency.controllers;

import com.exercise.travelAgency.exceptions.hotelNotFoundException;
import com.exercise.travelAgency.models.hotel;
import com.exercise.travelAgency.repositories.hotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class hotelController {
    @Autowired
    private hotelRepository repository;

    public hotelController() {}

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/hotels")
    CollectionModel<hotel> all() {

        List<hotel> hotels = repository.findAll().stream()
                .collect(Collectors.toList());

        return CollectionModel.of(hotels, linkTo(methodOn(hotelController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/hotels")
    hotel newHotel(@RequestBody hotel newhotel) {
        return repository.save(newhotel);
    }

    @GetMapping("/hotels/{id}")
    hotel one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new hotelNotFoundException(id));
    }

    @PutMapping("/hotels/{id}")
    hotel replaceHotel(@RequestBody hotel newhotel, @PathVariable Integer id) {

        return repository.findById(id)
                .map(hotel -> {
                    hotel.setHotelName(newhotel.getHotelName());
                    hotel.setHotelService(newhotel.getHotelService());
                    return repository.save(hotel);
                })
                .orElseGet(() -> {
                    newhotel.setId(id);
                    return repository.save(newhotel);
                });
    }

    @DeleteMapping("/hotels/{id}")
    void deleteHotel(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}
