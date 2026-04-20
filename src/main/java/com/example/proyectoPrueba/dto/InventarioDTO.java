package com.example.proyectoPrueba.dto;

import com.example.proyectoPrueba.model.Producto;

import java.util.List;

public record InventarioDTO(
        List<Producto> productos
) {
}
