//package com.example.backend.auth.util;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    // Use a long secret (at least 32 bytes). Replace with env var in prod.
//    private static final String SECRET = "replace_this_with_long_secure_secret_which_is_at_least_32_bytes";
//    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
//    private final long EXP_MILLIS = 1000L * 60 * 60 * 5; // 5 hours
//
//    public String generateToken(String username) {
//        long now = System.currentTimeMillis();
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + EXP_MILLIS))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return parseClaims(token).getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        return parseClaims(token).getExpiration().before(new Date());
//    }
//
//    public boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
