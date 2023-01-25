package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reservationRepository extends JpaRepository<reservation, Integer> {
    public List<reservation> findByClientName(String firstName, String lastName);
}
