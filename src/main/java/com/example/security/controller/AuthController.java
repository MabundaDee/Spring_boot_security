package com.example.security.controller;

import com.example.security.repository.UserRepository;
import com.example.security.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuntheticationService service;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists";
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return "User created successfully";

    }
    @GetMapping("/login")
    public String loginUser() {
        return "Login successful";
    }
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello, Admin!";
    }
    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello, User!";
    }
}