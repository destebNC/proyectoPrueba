package com.destebnc.supermercado.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/** id es de solo lectura. products es opcional al crear y se ignora al actualizar. */
public record InventoryDto(
        Integer id,
        @NotBlank(message = "El nombre del inventario es obligatorio") String name,
        List<@Valid ProductDto> products
) {}
