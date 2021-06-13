package com.qa.hobbyproject.dto;

public class VehicleDTO {
	private Long id;

	private String registration;

	private String make;

	private String model;

	private String colour;

	private int horsePower;

	public VehicleDTO() {
	}

	public VehicleDTO(String registration, String make, String model, String colour, int horsePower) {
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.horsePower = horsePower;
	}

	public VehicleDTO(Long id, String registration, String make, String model, String colour, int horsePower) {
		this.id = id;
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.horsePower = horsePower;
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

}
