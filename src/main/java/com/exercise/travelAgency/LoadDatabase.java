package com.exercise.travelAgency;

import com.exercise.travelAgency.models.EmailDetails;
import com.exercise.travelAgency.models.Hotel;
import com.exercise.travelAgency.models.reservation;
import com.exercise.travelAgency.models.travelPkg;
import com.exercise.travelAgency.repositories.EmailDetailsRepository;
import com.exercise.travelAgency.repositories.HotelRepository;
import com.exercise.travelAgency.repositories.reservationRepository;
import com.exercise.travelAgency.repositories.travelPkgRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
/*
* Class that preloads the database with some initial data to operate with.
* */
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(reservationRepository repository, travelPkgRepository repo, HotelRepository hotelrepo, EmailDetailsRepository emailrepo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return args -> {
            //One travel package, two reservations, a Hotel, and an empty EmailDetails are added.
            log.info("Preloading " + repo.save(new travelPkg("Vueling", "Ritz", "Paris", "B&B", 750.50f, dateFormat.parse("31/01/2023"), new Date(), LocalDateTime.from(dtFormat.parse("31/01/2023 16:30")))));
            log.info("Preloading " + repository.save(new reservation("Bilbo", "Baggins", new Date(), 50.50f)));
            log.info("Preloading " + repository.save(new reservation("Frodo", "Baggins", new Date(), 257.75f)));
            log.info("Preloading " + hotelrepo.save(new Hotel("Ritz", "B&B")));
            log.info("Preloading " + emailrepo.save(new EmailDetails()));

        };
    }

}