package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventarioDTO;
import com.example.proyectoPrueba.dto.ProductoDto;
import com.example.proyectoPrueba.model.Inventario;
import com.example.proyectoPrueba.model.Producto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public ProductoDto toDto(Producto product) {
        return new ProductoDto(
                product.getName(),
                product.getPrice()
        );
    }

    public InventarioDTO toDto(Inventario inventario) {

        List<ProductoDto> productosDto = inventario.getProductList()
                .stream()
                .map(this::toDto)
                .toList();

        return new InventarioDTO(productosDto);
    }
}