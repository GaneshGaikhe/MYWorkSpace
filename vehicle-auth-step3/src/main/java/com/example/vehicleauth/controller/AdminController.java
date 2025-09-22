package com.example.vehicleauth.controller;

import com.example.vehicleauth.model.User;
import com.example.vehicleauth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/promote/{username}")
    public ResponseEntity<?> promote(@PathVariable String username) {
        User u = userService.promoteToAdmin(username);
        return ResponseEntity.ok("Promoted: " + u.getUsername());
    }
}
