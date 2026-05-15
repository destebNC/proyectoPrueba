package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.LoginRequestDto;
import com.example.proyectoPrueba.dto.RegisterRequestDto;
import com.example.proyectoPrueba.dto.TokenResponse;
import com.example.proyectoPrueba.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    // Solo inyectamos el AuthService
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(operationId = "authLoginPost", summary = "User login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDto request) {
        String jwt = authService.login(request);
        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    @PostMapping("/register")
    @Operation(operationId = "authRegisterPost", summary = "User registration")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequestDto request) {
        authService.register(request);
        // Hacemos auto-login después de registrar para devolver el token
        String jwt = authService.login(new LoginRequestDto(request.username(), request.password()));
        return ResponseEntity.ok(new TokenResponse(jwt));
    }
}