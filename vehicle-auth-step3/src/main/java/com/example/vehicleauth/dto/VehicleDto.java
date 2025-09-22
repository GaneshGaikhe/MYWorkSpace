package com.example.vehicleauth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class VehicleDto {
    @NotBlank(message = "model is required")
    private String model;

    @NotBlank(message = "make is required")
    private String make;

    private String color;
}
