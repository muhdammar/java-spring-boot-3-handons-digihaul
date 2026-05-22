package com.example.javaspringboot3handonsdigihaul.controller;

import com.example.javaspringboot3handonsdigihaul.service.AuthService;
import com.example.javaspringboot3handonsdigihaul.dto.auth.AuthResponse;
import com.example.javaspringboot3handonsdigihaul.dto.auth.LoginRequest;
import com.example.javaspringboot3handonsdigihaul.dto.auth.MessageResponse;
import com.example.javaspringboot3handonsdigihaul.dto.auth.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}

