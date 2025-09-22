package com.example.backend.auth.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.auth.entity.RefreshToken;
import com.example.backend.auth.entity.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);
}
