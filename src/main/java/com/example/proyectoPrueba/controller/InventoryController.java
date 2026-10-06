package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Gestion de inventarios. Solo rol ADMIN (ver SecurityConfig). */
@RestController
@RequestMapping("/api/inv")
@Tag(name = "Inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "apiInvPost", summary = "Create inventory")
    public InventoryDto create(@Valid @RequestBody InventoryDto inventoryDto) {
        return inventoryService.create(inventoryDto);
    }

    @GetMapping
    @Operation(operationId = "apiInvGet", summary = "List all inventories")
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("/paginated")
    @Operation(operationId = "apiInvPaginatedGet", summary = "List inventories (Paginated)")
    public Page<InventoryDto> getPaginated(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return inventoryService.getPaginated(page, size);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "apiInvIdGet", summary = "Get inventory by ID")
    public InventoryDto getById(@PathVariable Integer id) {
        return inventoryService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(operationId = "apiInvIdPut", summary = "Rename inventory")
    public InventoryDto update(@PathVariable Integer id, @Valid @RequestBody InventoryDto inventoryDto) {
        return inventoryService.update(id, inventoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "apiInvIdDelete", summary = "Delete inventory and its products")
    public void delete(@PathVariable Integer id) {
        inventoryService.delete(id);
    }
}
