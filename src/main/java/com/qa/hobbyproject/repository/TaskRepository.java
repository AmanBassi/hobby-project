package com.qa.hobbyproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobbyproject.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByVehicleId(Long id);

}
