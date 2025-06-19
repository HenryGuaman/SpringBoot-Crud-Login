package com.example.demo.controller;
import com.example.demo.models.dao.UsersDAO;
import com.example.demo.models.entity.Users;
import com.example.demo.models.entity.JwtUtil;
import com.example.demo.service.Impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersDAO userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CustomUserDetailsService userDetailsService() {
            return Mockito.mock(CustomUserDetailsService.class);
        }

        @Bean
        public JwtUtil jwtUtil() {
            return Mockito.mock(JwtUtil.class);
        }

        @Bean
        @Primary
        public AuthenticationManager authManager() {
            return Mockito.mock(AuthenticationManager.class);
        }
    }

    private Users user;

    @BeforeEach
    void setup() {
        user = new Users();
        user.setUsername("admin456");
        user.setPassword("123456");
        user.setEmail("admin@test.com");
    }

    @Test
    void testRegisterUserSuccess() throws Exception {
        Mockito.when(userRepository.findByUsername("admin456")).thenReturn(null);
        Mockito.when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "username": "admin456",
                                "password": "123456",
                                "email": "admin@test.com"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario registrado correctamente"));
    }

    @Test
    void testRegisterUserExists() throws Exception {
        Mockito.when(userRepository.findByUsername("admin456")).thenReturn(user);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "admin456",
                                    "password": "123456",
                                    "email": "admin@test.com"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("El usuario ya existe"));
    }
}