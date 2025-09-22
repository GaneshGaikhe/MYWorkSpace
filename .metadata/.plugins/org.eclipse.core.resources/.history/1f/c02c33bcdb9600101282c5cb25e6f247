package com.example.vehicleauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final List<Map<String, String>> store = new ArrayList<>();

    @GetMapping("/view")
    public ResponseEntity<?> viewVehicles() {
        return ResponseEntity.ok(store);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addVehicle(@RequestBody Map<String, String> vehicle) {
        store.add(vehicle);
        return ResponseEntity.ok("Vehicle added");
    }
}
