package com.exercise.travelAgency;

import org.springframework.data.jpa.repository.JpaRepository;

interface reservationRepository extends JpaRepository<reservation, Integer> {
}
