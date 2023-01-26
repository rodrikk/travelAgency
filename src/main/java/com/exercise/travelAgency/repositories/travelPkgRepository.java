package com.exercise.travelAgency.repositories;

import com.exercise.travelAgency.models.travelPkg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface travelPkgRepository extends JpaRepository<travelPkg, Integer> {
    public List<travelPkg> findByDepartDate(Date departDate);
}
