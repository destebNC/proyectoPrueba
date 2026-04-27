package com.example.proyectoPrueba;

import com.example.proyectoPrueba.dto.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public TokenResponse login() {
        String fakeJwt = "token-de-prueba";
        return new TokenResponse(fakeJwt);
    }
}