package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDTO;
import com.example.proyectoPrueba.dto.InventoryDTO;
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

    public InventoryDTO inventoryToDto(Inventory inventory) {

        return new InventoryDTO(
                inventory.getName(),
                inventory.getProductList()
        );
    }
}