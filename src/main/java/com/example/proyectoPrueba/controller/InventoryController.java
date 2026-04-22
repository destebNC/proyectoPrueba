package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/addInv")
    public String create(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        return "Inventario creado";
    }

    @GetMapping("/inventories")
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    @DeleteMapping("/deleteInv/{inventory_id}")
    public String deleteInv(
            @PathVariable("inventory_id") Integer inventoryId
    ){
        inventoryService.delete(inventoryId);
        return "Inventario eliminado con éxito";
    }

    @PutMapping("/updateInv/{inventory_id}")
    public String update(
            @PathVariable("inventory_id")Integer inventoryId,
            @RequestBody Inventory inventory
    ){
        inventoryService.updateInv(inventoryId, inventory);
        return "Los datos del inventario de ID "+inventoryId+" han sido actualizados con éxito";
    }

}