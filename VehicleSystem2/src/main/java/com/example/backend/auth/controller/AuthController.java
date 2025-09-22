package com.example.backend.auth.controller;

import com.example.backend.auth.dto.AuthRequest;
import com.example.backend.auth.dto.AuthResponse;
import com.example.backend.auth.entity.User;
import com.example.backend.auth.exception.CustomAppException;
import com.example.backend.auth.repository.UserRepository;
import com.example.backend.auth.security.JwtUtil;
import com.example.backend.auth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        User u = userService.registerUser(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("User registered with id: " + u.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new CustomAppException("Invalid username or password", org.springframework.http.HttpStatus.UNAUTHORIZED);
        }

        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new CustomAppException("User not found", org.springframework.http.HttpStatus.NOT_FOUND));

        var roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), roles);
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new CustomAppException("Invalid refresh token", org.springframework.http.HttpStatus.UNAUTHORIZED);
        }

        String username = jwtUtil.getUsernameFromToken(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomAppException("User not found", org.springframework.http.HttpStatus.NOT_FOUND));

        var roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
        String accessToken = jwtUtil.generateAccessToken(username, roles);
        String newRefresh = jwtUtil.generateRefreshToken(username);

        return ResponseEntity.ok(new AuthResponse(accessToken, newRefresh));
    }
}
