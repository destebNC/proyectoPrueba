package com.example.proyectoPrueba.dto;

import com.example.proyectoPrueba.model.Product;
import java.util.List;

public record InventoryDto(
        String name,
        List<Product> products
) {}