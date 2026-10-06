package com.destebnc.supermercado.service;

import com.destebnc.supermercado.dto.InventoryDto;
import com.destebnc.supermercado.dto.ProductDto;
import com.destebnc.supermercado.model.Inventory;
import com.destebnc.supermercado.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/** Convierte entidades JPA <-> DTOs. La API nunca expone las entidades directamente. */
@Component
public class Mapper {

    public ProductDto productToDto(Product product) {
        Integer inventoryId = product.getInventory() != null ? product.getInventory().getId() : null;
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getWeight(),
                inventoryId
        );
    }

    public Product dtoToProduct(ProductDto productDto) {
        return new Product(productDto.name(), productDto.price(), productDto.weight());
    }

    public InventoryDto inventoryToDto(Inventory inventory) {
        List<ProductDto> products = inventory.getProducts().stream()
                .map(this::productToDto)
                .toList();
        return new InventoryDto(inventory.getId(), inventory.getName(), products);
    }

    public Inventory dtoToInventory(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory(inventoryDto.name());
        if (inventoryDto.products() != null) {
            inventoryDto.products().forEach(p -> inventory.addProduct(dtoToProduct(p)));
        }
        return inventory;
    }
}
