package com.example.demo.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dao.UsersDAO;
import com.example.demo.models.entity.JwtUtil;
import com.example.demo.models.entity.Users;
import com.example.demo.service.Impl.CustomUserDetailsService;

@RestController
@CrossOrigin // Si usas frontend React
public class UsersController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsersDAO userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "El usuario ya existe"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(Collections.singletonMap("message", "Usuario registrado correctamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales incorrectas"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}