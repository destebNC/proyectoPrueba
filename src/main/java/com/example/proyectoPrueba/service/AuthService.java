package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.JwtUtil; // Aseguramos el import correcto
import com.example.proyectoPrueba.dto.LoginRequestDto;
import com.example.proyectoPrueba.dto.RegisterRequestDto;
import com.example.proyectoPrueba.model.User;
import com.example.proyectoPrueba.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ELIMINADO: JwtUtil del constructor
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequestDto dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role()); // Guardamos el rol que viene del DTO

        userRepository.save(user);
    }

    public String login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (passwordEncoder.matches(dto.password(), user.getPassword())) {
            // Llamada ESTÁTICA a tu utilidad JWT
            return JwtUtil.generateToken(user.getUsername(), user.getRole());
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}