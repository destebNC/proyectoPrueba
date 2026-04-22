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
    //Inyecciones
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    // guardar un inventario
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    // mostrar todos los productos
    public List<InventoryDto> getAll() {
        return inventoryRepository.findAll().stream().map(inv -> {
            InventoryDto dto = new InventoryDto();
            dto.setId(inv.getId());
            dto.setName(inv.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    // borrar un inventario
    public void delete(Integer inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }

    // actualizar un inventario
    public void updateInv(Integer inventoryId, Inventory newInventory){
        Inventory inventory=inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new RuntimeException("Inventario de ID" +inventoryId+ " no encontrado"));

        inventory.setName(newInventory.getName());
    }
}