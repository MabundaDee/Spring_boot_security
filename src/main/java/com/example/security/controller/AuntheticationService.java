package com.example.security.controller;

import com.example.security.repository.UserRepository;
import com.example.security.service.JwtService;
import com.example.security.user.Role;
import com.example.security.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuntheticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuntheticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Role_USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuntheticationResponse.builder()
                .authenticationToken(jwtToken)
                .build();
    }
    public AuntheticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuntheticationResponse.builder()
                .authenticationToken(jwtToken)
                .build();


    }
}
