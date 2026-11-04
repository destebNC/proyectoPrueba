package com.destebnc.supermercado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * id e inventoryId son de solo lectura: se devuelven en las respuestas
 * y se ignoran al crear o actualizar.
 */
public record ProductDto(
        Integer id,
        @NotBlank(message = "El nombre es obligatorio") @Size(max = 100, message = "El nombre del producto no puede exceder los 100 caracteres") String name,
        @NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser positivo") Double price,
        @PositiveOrZero(message = "El peso no puede ser negativo") Double weight,
        Integer inventoryId
) {}
