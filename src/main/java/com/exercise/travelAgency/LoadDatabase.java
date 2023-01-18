package com.exercise.travelAgency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(reservationRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new reservation("Bilbo", "Baggins", new Date(), 50.50f)));
            log.info("Preloading " + repository.save(new reservation("Frodo", "Baggins", new Date(), 257.75f)));
        };
    }
    /*
    @Bean
    CommandLineRunner initDatabase(travelPkgRepository repository) {

        return args -> {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            log.info("Preloading " + repository.save(new travelPkg("Ryanair", "NH", "New Zealand", "B&B", 90f, (Date) dateFormat.parse("27/01/2023"), new Date(), (LocalDateTime) dtFormat.parse("27/01/2023 12:45"))));
            log.info("Preloading " + repository.save(new travelPkg("Vueling", "Ritz", "Paris", "B&B", 750.50f, (Date) dateFormat.parse("31/01/2023"), new Date(), (LocalDateTime) dtFormat.parse("31/01/2023 16:30"))));
        };
    }*/
}