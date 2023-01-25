package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.emailDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface emailDetailsRepository extends JpaRepository<emailDetails, Integer> {
}
