package com.example.proyectoPrueba.dto;

import java.util.List;

public record InventoryDto(
        List<ProductDto> productos,
        String name
) {
}
