package com.example.backend.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class VehicleDto {
    @NotBlank(message = "model is required")
    private String model;

    @NotBlank(message = "make is required")
    private String make;

    private String color;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
    
    
    
}
