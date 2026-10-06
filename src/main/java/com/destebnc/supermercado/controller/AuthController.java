package com.destebnc.supermercado.controller;

import com.destebnc.supermercado.dto.LoginRequestDto;
import com.destebnc.supermercado.dto.RegisterRequestDto;
import com.destebnc.supermercado.dto.TokenResponse;
import com.destebnc.supermercado.service.AuthService;
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
    @Operation(operationId = "login", summary = "User login")
    public TokenResponse login(@Valid @RequestBody LoginRequestDto request) {
        return new TokenResponse(authService.login(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "register", summary = "User registration (role USER)")
    public TokenResponse register(@Valid @RequestBody RegisterRequestDto request) {
        return new TokenResponse(authService.register(request));
    }
}
