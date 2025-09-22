//package com.example.backend.auth.service;
//
//import com.example.backend.auth.entity.User;
//import com.example.backend.auth.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        String[] authorities = user.getRoles().stream()
//                .map(role -> "ROLE_" + role.getName())
//                .collect(Collectors.toList())
//                .toArray(new String[0]);
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(authorities)
//                .build();
//    }
//}
