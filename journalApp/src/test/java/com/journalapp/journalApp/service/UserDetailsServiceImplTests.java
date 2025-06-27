package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {
        // Setup test user (your custom User class)
        User testUser = new User();
        testUser.setUsername("ram");
        testUser.setPassword("r@123");  // NOTE: This must be bcrypt-hashed if your real app expects it
        testUser.setRoles(List.of("ADMIN"));

        // Mock the repo to return Optional<User>
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(testUser));

        // Call the method
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");

        // Assertions
        assertNotNull(userDetails);
        assertEquals("ram", userDetails.getUsername());
        assertEquals("r@123", userDetails.getPassword());  // Use encoded password in real tests
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }
}
