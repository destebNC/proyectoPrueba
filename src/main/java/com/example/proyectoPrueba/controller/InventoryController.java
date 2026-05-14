package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inv") // Prefijo único para inventarios
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public String create(@Valid @RequestBody InventoryDto inventoryDto) {
        inventoryService.save(inventoryDto); // Ahora los dos usan InventoryDto
        return "Inventario creado";
    }

    @GetMapping("/paginated")
    public Page<InventoryDto> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return inventoryService.getInventoryPaginated(page, size);
    }

    @DeleteMapping("/{id}")
    public String deleteInv(@PathVariable Integer id) {
        inventoryService.delete(id);
        return "Inventario eliminado";
    }
}