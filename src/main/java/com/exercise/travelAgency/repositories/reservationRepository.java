package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reservationRepository extends JpaRepository<reservation, Integer> {
}
