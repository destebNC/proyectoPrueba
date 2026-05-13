package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import org.springframework.stereotype.Component;

import java.util.Collections; // Importar Collections
import java.util.List;
import java.util.Optional; // Importar Optional
import java.util.stream.Collectors;

@Component
public class Mapper {

    public ProductDto productToDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getWeight()
        );
    }

    public Product dtoToProduct(ProductDto productDto) {
        Product product = new Product();
        // Correcto para records
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setWeight(productDto.weight());
        return product;
    }

    public InventoryDto inventoryToDto(Inventory inventory) {
        // Manejo de null para productList en Inventory (modelo)
        List<ProductDto> productDtos = Optional.ofNullable(inventory.getProductList())
                                                .orElseGet(Collections::emptyList) // Si es null, usa una lista vacía
                                                .stream()
                                                .map(this::productToDto)
                                                .collect(Collectors.toList());

        return new InventoryDto(
                inventory.getName(),
                productDtos
        );
    }

    public Inventory dtoToInventory(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory();
        // Correcto para records: usar .name()
        inventory.setName(inventoryDto.name());

        // Manejo de null para products en InventoryDto (record)
        List<Product> products = Optional.ofNullable(inventoryDto.products())
                                        .orElseGet(Collections::emptyList) // Si es null, usa una lista vacía
                                        .stream()
                                        .map(this::dtoToProduct)
                                        .collect(Collectors.toList());
        inventory.setProductList(products);

        return inventory;
    }
}