package com.exercise.travelAgency;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

	//Bean used to make the modelMapper constructor available throughout the entire Springboot application where needed.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
