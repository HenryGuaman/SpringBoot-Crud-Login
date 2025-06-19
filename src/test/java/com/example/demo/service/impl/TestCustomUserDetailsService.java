package com.example.demo.service.impl;

import com.example.demo.models.dao.UsersDAO;
import com.example.demo.models.entity.Users;
import com.example.demo.service.Impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestCustomUserDetailsService {

    @Autowired
    private UsersDAO userRepository;  // Mock del repositorio

    private CustomUserDetailsService service;  // Clase a testear

    @BeforeEach
    void setUp() {
        // Crear mock del repositorio
        userRepository = Mockito.mock(UsersDAO.class);
        service = new CustomUserDetailsService(userRepository);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("pass123");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        UserDetails userDetails = service.loadUserByUsername("testuser");

        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("pass123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());  // <- cambia aquí
    }


    @Test
    void loadUserByUsername_UserNotFound_ThrowsException() {
        // Arrange: el repositorio devuelve null para el usuario buscado
        when(userRepository.findByUsername("missinguser")).thenReturn(null);

        // Act & Assert: esperamos que lance UsernameNotFoundException
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("missinguser");
        });

        assertEquals("User not found", exception.getMessage());
    }
}
