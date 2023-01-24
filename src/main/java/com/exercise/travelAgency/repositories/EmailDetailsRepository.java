package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDetailsRepository extends JpaRepository<EmailDetails, Integer> {
}
