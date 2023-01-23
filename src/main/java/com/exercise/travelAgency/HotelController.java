package com.exercise.travelAgency;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HotelController {
    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/hotels")
    CollectionModel<Hotel> all() {

        List<Hotel> hotels = repository.findAll().stream()
                .collect(Collectors.toList());

        return CollectionModel.of(hotels, linkTo(methodOn(HotelController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/hotels")
    Hotel newHotel(@RequestBody Hotel newhotel) {
        return repository.save(newhotel);
    }

    @GetMapping("/hotels/{id}")
    Hotel one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @PutMapping("/hotels/{id}")
    Hotel replaceHotel(@RequestBody Hotel newhotel, @PathVariable Integer id) {

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
