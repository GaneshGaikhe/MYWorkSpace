package com.example.backend.auth.service;

import com.example.backend.auth.entity.Role;
import com.example.backend.auth.entity.User;
import com.example.backend.auth.exception.CustomAppException;
import com.example.backend.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

/**
 * Service class for user management.
 * Handles registration, promotion, and user-related business logic.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with default role.
     *
     * @param username raw username
     * @param rawPassword raw password
     * @return saved User entity
     */
    public User registerUser(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new CustomAppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_USER))); // Default role is USER

        return userRepository.save(user);
    }

    /**
     * Promotes an existing user to ADMIN role.
     *
     * @param username target username
     * @return updated User entity
     */
    public User promoteToAdmin(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomAppException("User not found", HttpStatus.NOT_FOUND));

        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new CustomAppException("User is already an admin", HttpStatus.BAD_REQUEST);
        }

        user.getRoles().add(Role.ROLE_ADMIN);
        return userRepository.save(user);
    }
}
