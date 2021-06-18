package com.qa.hobbyproject.service;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.qa.hobbyproject.domain.Task;
import com.qa.hobbyproject.dto.TaskDTO;
import com.qa.hobbyproject.repository.TaskRepository;

@SpringBootTest
@ActiveProfiles("test")
class TaskServiceTest {

	@Autowired
	private TaskService service;

	@MockBean
	private TaskRepository repository;

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
	void testTaskService() {
		TaskService vts = new TaskService(repository, mapper);
		assertThat(vts).isInstanceOf(TaskService.class);
	}

	@Test
	void testAddTask() {
		Task createdTask = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));
		TaskDTO savedTask = new TaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));

		// GIVEN
		TaskDTO taskDTO = new TaskDTO(null, "MOT", LocalDate.of(2021, 7, 1));
		Task task = new Task("MOT", LocalDate.of(2021, 7, 1));
		
		// WHEN
		Mockito.when(this.mapper.map(taskDTO, Task.class)).thenReturn(task);
		Mockito.when(this.repository.save(task)).thenReturn(createdTask);
		Mockito.when(this.mapper.map(createdTask, TaskDTO.class)).thenReturn(savedTask);

		// THEN
		assertThat(this.service.addTask(1L, taskDTO)).isEqualTo(savedTask);

		Mockito.verify(this.repository, Mockito.times(1)).save(task);
		Mockito.verify(this.mapper, Mockito.times(1)).map(createdTask, TaskDTO.class);
	}

	@Test
	void testGetTasksByVehicleId() {
		// GIVEN
		List<Task> tasks = new ArrayList<>();

		Task task1 = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));
		Task task2 = new Task(2L, "Service", LocalDate.of(2021, 8, 2));

		tasks.add(task1);
		tasks.add(task2);

		List<TaskDTO> taskDTOs = new ArrayList<>();

		TaskDTO taskDTO1 = new TaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
		TaskDTO taskDTO2 = new TaskDTO(2L, "Service", LocalDate.of(2021, 8, 2));

		taskDTOs.add(taskDTO1);
		taskDTOs.add(taskDTO2);

		// WHEN
		Mockito.when(this.repository.findAllByVehicleId(1L)).thenReturn(tasks);
		Mockito.when(this.mapper.map(task1, TaskDTO.class)).thenReturn(taskDTO1);
		Mockito.when(this.mapper.map(task2, TaskDTO.class)).thenReturn(taskDTO2);

		// THEN
		assertThat(this.service.getTasksByVehicleId(1L)).isEqualTo(taskDTOs);

		Mockito.verify(this.repository, Mockito.times(1)).findAllByVehicleId(1L);
		Mockito.verify(this.mapper, Mockito.times(1)).map(task1, TaskDTO.class);
		Mockito.verify(this.mapper, Mockito.times(1)).map(task2, TaskDTO.class);
	}

	@Test
	void testUpdateTask() {
		// GIVEN
		Task task = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));
		TaskDTO existingTask = new TaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
		Optional<Task> optionalExistingTask = Optional.ofNullable(new Task(1L, "Service", LocalDate.of(2021, 8, 2)));
		Task updatedTask = new Task(1L, "Service", LocalDate.of(2021, 8, 2));
		TaskDTO updatedTaskDTO = new TaskDTO(1L, "Service", LocalDate.of(2021, 8, 2));

		// WHEN
		Mockito.when(this.repository.findById(1L)).thenReturn(optionalExistingTask);
		Mockito.when(this.repository.save(task)).thenReturn(updatedTask);
		Mockito.when(this.mapper.map(updatedTask, TaskDTO.class)).thenReturn(updatedTaskDTO);

		// THEN
		assertThat(this.service.updateTask(1L, existingTask)).isEqualTo(updatedTaskDTO);

		Mockito.verify(this.repository, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repository, Mockito.times(1)).save(task);
		Mockito.verify(this.mapper, Mockito.times(1)).map(updatedTask, TaskDTO.class);
	}

	@Test
	void testDeleteTask() {
		// GIVEN
		Long taskId = 1L;

		// WHEN
		Mockito.when(this.repository.existsById(taskId)).thenReturn(false);

		// THEN
		assertThat(this.service.deleteTask(taskId)).isTrue();

		Mockito.verify(this.repository, Mockito.times(1)).existsById(taskId);
	}

	@Test
	void testDeleteTaskNotFound() {
		// GIVEN
		Long taskId = 99L;

		// WHEN
		Mockito.when(this.repository.existsById(taskId)).thenReturn(true);

		// THEN
		assertThat(this.service.deleteTask(taskId)).isFalse();

		Mockito.verify(this.repository, Mockito.times(1)).existsById(taskId);
	}

}
