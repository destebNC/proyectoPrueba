package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDTO;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getPrice()
        );
    }

    public InventoryDTO toDto(Inventory inventory) {

        List<ProductDto> productosDto = inventory.getProductList()
                .stream()
                .map(this::toDto)
                .toList();

        return new InventoryDTO(productosDto);
    }
}