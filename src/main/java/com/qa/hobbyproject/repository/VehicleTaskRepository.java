package com.qa.hobbyproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobbyproject.domain.VehicleTask;

public interface VehicleTaskRepository extends JpaRepository<VehicleTask, Long> {
	
	List<VehicleTask> findAllByVehicleId(Long id);

}
