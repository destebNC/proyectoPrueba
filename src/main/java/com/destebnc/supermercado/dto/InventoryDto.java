package com.destebnc.supermercado.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/** id es de solo lectura. products es opcional al crear y se ignora al actualizar. */
public record InventoryDto(
        Integer id,
        @NotBlank(message = "El nombre del inventario es obligatorio") 
        @Size(max = 100, message = "El nombre del inventario no puede exceder los 100 caracteres") 
        String name,

        @Size(max=100,  message="La lista de productos no puede exceder los 100 elementos")
        List<@Valid ProductDto> products
) {}
