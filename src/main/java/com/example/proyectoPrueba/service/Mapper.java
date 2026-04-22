package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public ProductDto productToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getWeight()
        );
    }

    public InventoryDto inventoryToDto(Inventory inventory) {

        List<Product> products = inventory.getProductList();

        return new InventoryDto(
                inventory.getName(),
                products
        );
    }
}