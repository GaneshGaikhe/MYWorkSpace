package com.example.backend.auth.controller;
import com.example.backend.auth.dto.AuthRequest;
import com.example.backend.auth.dto.AuthResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

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

    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@RequestBody Map<String, String> vehicle) {
        store.add(vehicle);
        return ResponseEntity.ok("Vehicle added");
    }
}
