package com.example.backend.auth.service;

import com.example.backend.auth.dto.VehicleDto;
import com.example.backend.auth.entity.Vehicle;
import com.example.backend.auth.exception.CustomAppException;
import com.example.backend.auth.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Create
    public Vehicle addVehicle(VehicleDto dto) {
        if (dto.getModel() == null || dto.getModel().isBlank() ||
            dto.getMake() == null || dto.getMake().isBlank()) {
            throw new CustomAppException("Vehicle model and make are required", HttpStatus.BAD_REQUEST);
        }

        Vehicle v = new Vehicle();
        v.setModel(dto.getModel());
        v.setMake(dto.getMake());
        v.setColor(dto.getColor());
        return vehicleRepository.save(v);
    }

    // List all active vehicles
    public List<Vehicle> listVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll().stream()
                .filter(Vehicle::isActive)
                .toList();
        if (vehicles.isEmpty()) {
            throw new CustomAppException("No vehicles found", HttpStatus.NOT_FOUND);
        }
        return vehicles;
    }

    // Update
    public Vehicle updateVehicle(Long id, VehicleDto dto) {
        Vehicle v = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Vehicle not found", HttpStatus.NOT_FOUND));

        v.setModel(dto.getModel());
        v.setMake(dto.getMake());
        v.setColor(dto.getColor());
        return vehicleRepository.save(v);
    }

    // Soft delete
    public void deleteVehicle(Long id) {
        Vehicle v = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Vehicle not found", HttpStatus.NOT_FOUND));
        v.setActive(false);
        vehicleRepository.save(v);
    }
}
