package com.example.backend.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            System.out.println("[DEBUG] Found token: " + token);
        } else {
            System.out.println("[DEBUG] No Bearer token found in header");
        }

        if (token != null) {
            boolean valid = jwtUtil.validateToken(token);
            System.out.println("[DEBUG] Token valid? " + valid);

            if (valid && SecurityContextHolder.getContext().getAuthentication() == null) {
                String username = jwtUtil.getUsernameFromToken(token);
                Set<String> roles = jwtUtil.getRolesFromToken(token);
                System.out.println("[DEBUG] Username from token: " + username);
                System.out.println("[DEBUG] Roles from token: " + roles);

                List<SimpleGrantedAuthority> authorities = roles.stream()
                        // Ensure Spring Security sees them as "ROLE_USER", "ROLE_ADMIN", etc.
                        .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("[DEBUG] Authentication set in SecurityContext");
            }
        }

        chain.doFilter(request, response);
    }
    
    
}

