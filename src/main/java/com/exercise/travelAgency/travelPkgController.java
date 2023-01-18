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
class travelPkgController {

    private final travelPkgRepository repository;

    public travelPkgController(travelPkgRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/travelPkgs")
    List<travelPkg> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/travelPkgs")
    travelPkg newTravelPkg(@RequestBody travelPkg newtravelPkg) {
        return repository.save(newtravelPkg);
    }

    // Single item

    @GetMapping("/travelPkgs/{id}")
    travelPkg one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new travelPkgNotFoundException(id));
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