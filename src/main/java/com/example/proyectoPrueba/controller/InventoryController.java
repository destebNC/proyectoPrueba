package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//Linea de prueba jc
@RestController
@RequestMapping
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inv")
    public String create(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        return "Inventario creado";
    }

    @GetMapping("/inv")
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("/inv/{inventory_id}")
    public InventoryDto getById(
            @PathVariable("inventory_id") Integer inventoryId
    ){
        return inventoryService.getById(inventoryId);
    }

    @DeleteMapping("/inv/{inventory_id}")
    public String deleteInv(
            @PathVariable("inventory_id") Integer inventoryId
    ){
        inventoryService.delete(inventoryId);
        return "Inventario eliminado con éxito";
    }

    @PutMapping("/inv/{inventory_id}")
    public String update(
            @PathVariable("inventory_id")Integer inventoryId,
            @RequestBody Inventory inventory
    ){
        inventoryService.updateInv(inventoryId, inventory);
        return "Los datos del inventario de ID "+inventoryId+" han sido actualizados con éxito";
    }

}