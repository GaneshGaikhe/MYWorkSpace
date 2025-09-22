package com.example.vehicleauth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String make;
    private String color;
    private boolean active = true;
}
