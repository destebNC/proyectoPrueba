package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public ProductDto productToDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getWeight()
        );
    }

    public InventoryDto inventoryToDto(Inventory inventory) {

        return new InventoryDto(
                inventory.getName(),
        )
    }
}