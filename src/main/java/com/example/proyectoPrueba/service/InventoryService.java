package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final Mapper mapper;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository, Mapper mapper) {
        this.mapper=mapper;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public List<InventoryDto> getAll() {
        return inventoryRepository.findAll()
                .stream()
                .map(inv -> new InventoryDto(
                        inv.getName(),
                        inv.getProductList()
                                .stream()
                                .map(p -> new ProductDto(
                                        p.getName(),
                                        p.getPrice(),
                                        p.getWeight()
                                ))
                                .toList()
                ))
                .toList();
    }

    public void delete(Integer inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }

    public void updateInv(Integer inventoryId, Inventory newInventory) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario de ID " + inventoryId + " no encontrado"));

        inventory.setName(newInventory.getName());

        inventoryRepository.save(inventory);
    }

    public InventoryDto getById(Integer inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        return new InventoryDto(
                inventory.getName(),
                inventory.getProductList()
                        .stream()
                        .map(p -> new ProductDto(
                                p.getName(),
                                p.getPrice(),
                                p.getWeight()
                        ))
                        .toList()
        );
    }
}