package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.travelPkg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface travelPkgRepository extends JpaRepository<travelPkg, Integer> {
}
