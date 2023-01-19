package com.exercise.travelAgency;

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
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(reservationRepository repository, travelPkgRepository repo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return args -> {
            log.info("Preloading " + repo.save(new travelPkg("Vueling", "Ritz", "Paris", "B&B", 750.50f, dateFormat.parse("31/01/2023"), new Date(), LocalDateTime.from(dtFormat.parse("31/01/2023 16:30")))));
            log.info("Preloading " + repository.save(new reservation("Bilbo", "Baggins", new Date(), 50.50f)));
            log.info("Preloading " + repository.save(new reservation("Frodo", "Baggins", new Date(), 257.75f)));
        };
    }

}