package com.qa.hobbyproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobbyproject.domain.VehicleTask;
import com.qa.hobbyproject.dto.VehicleTaskDTO;
import com.qa.hobbyproject.repository.VehicleTaskRepository;

@SpringBootTest
@ActiveProfiles("test")
class VehicleTaskServiceTest {

	@Autowired
	private VehicleTaskService service;

	@MockBean
	private VehicleTaskRepository repository;

	@MockBean
	private ModelMapper mapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVehicleTaskService() {
		VehicleTaskService vts = new VehicleTaskService(repository, mapper);
		assertThat(vts).isInstanceOf(VehicleTaskService.class);
	}

	@Test
	void testAddVehicleTask() {
		VehicleTask createdTask = new VehicleTask(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTaskDTO savedTask = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));

		// GIVEN
		VehicleTask task = new VehicleTask("MOT", LocalDate.of(2021, 7, 1));

		// WHEN
		Mockito.when(this.repository.save(task)).thenReturn(createdTask);
		Mockito.when(this.mapper.map(createdTask, VehicleTaskDTO.class)).thenReturn(savedTask);

		// THEN
		assertThat(this.service.addVehicleTask(task)).isEqualTo(savedTask);

		Mockito.verify(this.repository, Mockito.times(1)).save(task);
		Mockito.verify(this.mapper, Mockito.times(1)).map(createdTask, VehicleTaskDTO.class);
	}

	@Test
	void testGetVehicleTasksByVehicleId() {
		// GIVEN
		List<VehicleTask> tasks = new ArrayList<>();

		VehicleTask task1 = new VehicleTask(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTask task2 = new VehicleTask(2L, "Service", LocalDate.of(2021, 8, 2));

		tasks.add(task1);
		tasks.add(task2);

		List<VehicleTaskDTO> taskDTOs = new ArrayList<>();

		VehicleTaskDTO taskDTO1 = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTaskDTO taskDTO2 = new VehicleTaskDTO(2L, "Service", LocalDate.of(2021, 8, 2));

		taskDTOs.add(taskDTO1);
		taskDTOs.add(taskDTO2);

		// WHEN
		Mockito.when(this.repository.findAllByVehicleId(1L)).thenReturn(tasks);
		Mockito.when(this.mapper.map(task1, VehicleTaskDTO.class)).thenReturn(taskDTO1);
		Mockito.when(this.mapper.map(task2, VehicleTaskDTO.class)).thenReturn(taskDTO2);

		// THEN
		assertThat(this.service.getVehicleTasksByVehicleId(1L)).isEqualTo(taskDTOs);

		Mockito.verify(this.repository, Mockito.times(1)).findAllByVehicleId(1L);
		Mockito.verify(this.mapper, Mockito.times(1)).map(task1, VehicleTaskDTO.class);
		Mockito.verify(this.mapper, Mockito.times(1)).map(task2, VehicleTaskDTO.class);
	}

	@Test
	void testUpdateVehicleTask() {
		// GIVEN
		VehicleTask existingTask = new VehicleTask(1L, "MOT", LocalDate.of(2021, 7, 1));
		Optional<VehicleTask> optionalExistingVehicleTask = Optional
				.ofNullable(new VehicleTask(1L, "Service", LocalDate.of(2021, 8, 2)));
		VehicleTask updatedVehicleTask = new VehicleTask(1L, "Service", LocalDate.of(2021, 8, 2));
		VehicleTaskDTO updatedVehicleTaskDTO = new VehicleTaskDTO(1L, "Service", LocalDate.of(2021, 8, 2));

		// WHEN
		Mockito.when(this.repository.findById(1L)).thenReturn(optionalExistingVehicleTask);
		Mockito.when(this.repository.save(existingTask)).thenReturn(updatedVehicleTask);
		Mockito.when(this.mapper.map(updatedVehicleTask, VehicleTaskDTO.class)).thenReturn(updatedVehicleTaskDTO);

		// THEN
		assertThat(this.service.updateVehicleTask(1L, existingTask)).isEqualTo(updatedVehicleTaskDTO);

		Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repository, Mockito.times(1)).save(existingTask);
		Mockito.verify(this.mapper, Mockito.times(1)).map(updatedVehicleTask, VehicleTaskDTO.class);
	}

	@Test
	void testDeleteVehicleTask() {
		// GIVEN
		Long taskId = 1L;

		// WHEN
		Mockito.when(this.repository.existsById(taskId)).thenReturn(false);

		// THEN
		assertThat(this.service.deleteVehicleTask(taskId)).isEqualTo(true);

		Mockito.verify(this.repository, Mockito.times(1)).existsById(taskId);
	}

}
