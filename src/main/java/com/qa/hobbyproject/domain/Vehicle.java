package com.qa.hobbyproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String registration;

	private String make;

	private String model;

	private String colour;

	private int horsePower;

	@OneToMany(mappedBy = "vehicle")
	@JsonIgnore
	private List<VehicleTask> tasks;

	public Vehicle() {
	}

	public Vehicle(String registration, String make, String model, String colour, int horsePower) {
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.horsePower = horsePower;
	}

	public Vehicle(Long id, String registration, String make, String model, String colour, int horsePower) {
		this.id = id;
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.horsePower = horsePower;
	}

	public Vehicle(Long id, String registration, String make, String model, String colour, int horsePower,
			List<VehicleTask> tasks) {
		this.id = id;
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.horsePower = horsePower;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public String getRegistration() {
		return registration;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getColour() {
		return colour;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public List<VehicleTask> getTasks() {
		return tasks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public void setTasks(List<VehicleTask> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", registration=" + registration + ", make=" + make + ", model=" + model
				+ ", colour=" + colour + ", horsePower=" + horsePower + ", tasks=" + tasks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + horsePower;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((registration == null) ? 0 : registration.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (horsePower != other.horsePower)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (registration == null) {
			if (other.registration != null)
				return false;
		} else if (!registration.equals(other.registration))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}

}
