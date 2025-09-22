package com.example.auth.service;
import com.example.auth.model.Role; import com.example.auth.model.User; import com.example.auth.repo.UserRepository; import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import static org.mockito.Mockito.*; import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private UserService userService;
    @Test void testRegisterUser_success() {
        when(userRepository.existsByUsername("alice")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("encoded");
        User saved = new User(); saved.setId(1L); saved.setUsername("alice"); saved.setPassword("encoded"); saved.setRoles(new HashSet<>(java.util.Collections.singletonList(Role.ROLE_USER)));
        when(userRepository.save(any())).thenReturn(saved);
        User res = userService.registerUser("alice","pass123"); assertEquals("alice", res.getUsername()); assertEquals("encoded", res.getPassword()); assertTrue(res.getRoles().contains(Role.ROLE_USER));
    }
}
