package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.JwtUtil;
import com.example.proyectoPrueba.dto.TokenResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public TokenResponse login() {

        String username = "user";
        String role = "ADMIN"; // o USER

        String jwt = JwtUtil.generateToken(username, role);

        return new TokenResponse(jwt);
    }
}