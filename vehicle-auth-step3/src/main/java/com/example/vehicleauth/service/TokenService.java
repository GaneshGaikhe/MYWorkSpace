package com.example.vehicleauth.service;

import com.example.vehicleauth.model.RefreshToken;
import com.example.vehicleauth.model.User;
import com.example.vehicleauth.repo.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refreshExpirationMs}")
    private long refreshDurationMs;

    public TokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken rt = new RefreshToken();
        rt.setUser(user);
        rt.setExpiryDate(Instant.now().plusMillis(refreshDurationMs));
        rt.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(rt);
    }

    public boolean isTokenExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }

    public void deleteRefreshTokensForUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }

    public RefreshTokenRepository getRepository() {
        return this.refreshTokenRepository;
    }
}
