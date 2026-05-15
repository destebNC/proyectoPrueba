package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto; // Importación correcta del DTO
import com.example.proyectoPrueba.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inv")
@Tag(name = "Inventories") // Alineado con el nombre del grupo en tu openapi.yaml manual
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    @Operation(operationId = "apiInvPost", summary = "Create inventory")
    public ResponseEntity<InventoryDto> create(@RequestBody InventoryDto inventoryDto) {
        // En tu service el método es save(InventoryDto inventoryDto) y es void
        inventoryService.save(inventoryDto);
        return ResponseEntity.ok(inventoryDto);
    }

    @GetMapping("/paginated")
    @Operation(operationId = "apiInvPaginatedGet", summary = "List inventories (Paginated)")
    public ResponseEntity<Page<InventoryDto>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // En tu service el método se llama getInventoryPaginated
        return ResponseEntity.ok(inventoryService.getInventoryPaginated(page, size));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "apiInvIdDelete", summary = "Delete inventory")
    public ResponseEntity<Void> deleteInv(@PathVariable Integer id) {
        // En tu service el método se llama delete(Integer inventoryId)
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}