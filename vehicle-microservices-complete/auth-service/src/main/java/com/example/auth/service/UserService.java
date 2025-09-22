package com.example.auth.service;
import com.example.auth.model.Role; import com.example.auth.model.User; import com.example.auth.repo.UserRepository; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
import java.util.Collections; import java.util.HashSet; import java.util.Optional; import java.util.Set;
@Service public class UserService {
    private final UserRepository userRepository; private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){ this.userRepository = userRepository; this.passwordEncoder = passwordEncoder; }
    public User registerUser(String username, String rawPassword){ if(userRepository.existsByUsername(username)){ throw new RuntimeException("Username already exists"); } User u = new User(); u.setUsername(username); u.setPassword(passwordEncoder.encode(rawPassword)); u.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_USER))); return userRepository.save(u); }
    public User promoteToAdmin(String username){ User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")); user.getRoles().add(Role.ROLE_ADMIN); return userRepository.save(user); }
    public Optional<User> findByUsername(String username){ return userRepository.findByUsername(username); }
}
