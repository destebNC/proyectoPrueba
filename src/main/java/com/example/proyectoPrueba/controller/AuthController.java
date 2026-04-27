package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.TokenResponse;
import com.example.proyectoPrueba.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public TokenResponse login() {
        String jwt = JwtUtil.generateToken("user");
        return new TokenResponse(jwt);
    }
}