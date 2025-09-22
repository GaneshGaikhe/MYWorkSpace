package com.example.vehicleauth.repo;

import com.example.vehicleauth.model.RefreshToken;
import com.example.vehicleauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);
}
