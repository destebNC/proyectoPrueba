package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.LoginRequestDto;
import com.example.proyectoPrueba.dto.RegisterRequestDto;
import com.example.proyectoPrueba.dto.TokenResponse;
import com.example.proyectoPrueba.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
@SecurityRequirements // endpoints publicos: no requieren token
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(operationId = "authLoginPost", summary = "User login")
    public TokenResponse login(@Valid @RequestBody LoginRequestDto request) {
        return new TokenResponse(authService.login(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "authRegisterPost", summary = "User registration (role USER)")
    public TokenResponse register(@Valid @RequestBody RegisterRequestDto request) {
        return new TokenResponse(authService.register(request));
    }
}
