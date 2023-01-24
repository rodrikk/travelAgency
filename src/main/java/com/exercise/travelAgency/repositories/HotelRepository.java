package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}