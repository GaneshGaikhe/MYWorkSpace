package com.example.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.auth.entity.User;
import com.example.backend.auth.entity.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> { }
