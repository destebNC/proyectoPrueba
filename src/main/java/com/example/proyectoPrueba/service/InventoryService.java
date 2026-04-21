package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public List<InventoryDto> getAll() {
        return inventoryRepository.findAll().stream().map(inv -> {
            InventoryDto dto = new InventoryDto();
            dto.setId(inv.getId());
            dto.setName(inv.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public void addProduct(Integer inventoryId, Product product) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario con ID " + inventoryId + " no encontrado"));
        product.setInventory(inventory);
        productRepository.save(product);
    }
}