// Linea prueba 1

package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
// import com.example.proyectoPrueba.model.Product; // Lo comento si no lo usas directamente aquí
import com.example.proyectoPrueba.service.InventoryService;
import org.springframework.data.domain.Page; // <-- IMPORTANTE: Añadimos esta importación
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

    // Tu método original que devuelve TODO de golpe
    @GetMapping("/inv")
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    // NUEVO MÉTODO: El que devuelve los datos paginados
    @GetMapping("/inv/paginated")
    public Page<InventoryDto> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return inventoryService.getInventoryPaginated(page, size);
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