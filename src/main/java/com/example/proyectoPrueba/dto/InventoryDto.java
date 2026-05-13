package com.example.proyectoPrueba.dto;

import java.util.List;

public record InventoryDto(
        String name,
        List<ProductDto> products
) {}