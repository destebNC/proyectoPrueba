package com.example.proyectoPrueba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * id e inventoryId son de solo lectura: se devuelven en las respuestas
 * y se ignoran al crear o actualizar.
 */
public record ProductDto(
        Integer id,
        @NotBlank(message = "El nombre es obligatorio") String name,
        @NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser positivo") Double price,
        @PositiveOrZero(message = "El peso no puede ser negativo") Double weight,
        Integer inventoryId
) {}
