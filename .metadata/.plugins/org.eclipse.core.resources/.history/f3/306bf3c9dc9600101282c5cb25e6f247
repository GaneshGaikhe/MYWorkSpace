//package com.example.backend.auth.filter;
//
//import com.example.backend.auth.service.CustomUserDetailsService;
//import com.example.backend.auth.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class SecurityConfig extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//    private final CustomUserDetailsService userDetailsService;
//
//    public SecurityConfig(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        final String prefix = "Bearer ";
//
//        String username = null;
//        String jwt = null;
//
//        if (authHeader != null && authHeader.startsWith(prefix)) {
//            jwt = authHeader.substring(prefix.length());
//            try {
//                username = jwtUtil.extractUsername(jwt);
//            } catch (Exception e) {
//                // token invalid or expired; do nothing and let security handle it
//            }
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            var userDetails = userDetailsService.loadUserByUsername(username);
//            if (jwtUtil.validateToken(jwt, (org.springframework.security.core.userdetails.UserDetails) userDetails)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
