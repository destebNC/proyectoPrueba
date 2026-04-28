package com.example.proyectoPrueba.dto;

public record RegisterRequestDto(
        String username,
        String password,
        String role
) {
}
