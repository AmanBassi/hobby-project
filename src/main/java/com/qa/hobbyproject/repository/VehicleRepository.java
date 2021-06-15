package com.qa.hobbyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobbyproject.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

}
