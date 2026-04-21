package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDTO;
import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService; // Debes crear este servicio

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Crear un nuevo tipo de inventario (Ej: "Carnicería")
    @PostMapping("/inventory")
    public String create(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        return "Inventario creado";
    }

    // Listar todos los inventarios
    @GetMapping("/inventories")
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    // Añadir un producto a un inventario específico
    @PostMapping("/inventory/{inventoryId}/products")
    public String addProductToInventory(
            @PathVariable Integer inventoryId,
            @RequestBody Product product) {
        inventoryService.addProduct(inventoryId, product);
        return "Producto añadido al inventario con éxito";
    }
}
