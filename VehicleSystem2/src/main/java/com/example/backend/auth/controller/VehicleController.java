package com.example.backend.auth.controller;

import com.example.backend.auth.dto.VehicleDto;
import com.example.backend.auth.entity.Vehicle;
import com.example.backend.auth.service.VehicleService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/view")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Vehicle>> viewVehicles() {
        return ResponseEntity.ok(vehicleService.listVehicles());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody VehicleDto dto) {
        return ResponseEntity.ok(vehicleService.addVehicle(dto));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDto dto) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
