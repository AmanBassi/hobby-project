package com.qa.hobbyproject.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private LocalDate dueDate;

	private String status;

	@ManyToOne
	private Vehicle vehicle;

	public Task() {
	}

	public Task(String name, LocalDate dueDate, String status) {
		this.name = name;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Task(Long id, String name, LocalDate dueDate, String status) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Task(String name, LocalDate dueDate, String status, Vehicle vehicle) {
		this.name = name;
		this.dueDate = dueDate;
		this.status = status;
		this.vehicle = vehicle;
	}

	public Task(Long id, String name, LocalDate dueDate, String status, Vehicle vehicle) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.status = status;
		this.vehicle = vehicle;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getStatus() {
		return status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", dueDate=" + dueDate + ", status=" + status + ", vehicle=" + vehicle + "]";
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		var result = 1;
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

}
