package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.JwtUtil;
import com.example.proyectoPrueba.dto.LoginRequestDto;
import com.example.proyectoPrueba.dto.RegisterRequestDto;
import com.example.proyectoPrueba.dto.TokenResponse;
import com.example.proyectoPrueba.model.User;
import com.example.proyectoPrueba.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor para inyectar el repository
    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequestDto request) {
        System.out.println(passwordEncoder.encode("1234"));

        // Buscar usuario por username
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generar JWT con user y rol
        String jwt = JwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        // Devolver token
        return new TokenResponse(jwt);
    }

    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegisterRequestDto request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(request.username());

        user.setPassword(passwordEncoder.encode(request.password()));

        user.setRole(request.role());;

        userRepository.save(user);

        String jwt = JwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new TokenResponse(jwt);
    }
}