package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface hotelRepository extends JpaRepository<hotel, Integer> {
}