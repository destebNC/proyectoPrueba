package com.destebnc.supermercado.controller;

import com.destebnc.supermercado.dto.InventoryDto;
import com.destebnc.supermercado.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** Gestion de inventarios. Solo rol ADMIN (ver SecurityConfig). */
@RestController
@RequestMapping("/api/inventories")
@Tag(name = "Inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @Operation(operationId = "listInventories", summary = "List inventories (paginated)")
    public Page<InventoryDto> list(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return inventoryService.list(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createInventory", summary = "Create inventory (optionally with products)")
    public InventoryDto create(@Valid @RequestBody InventoryDto inventoryDto) {
        return inventoryService.create(inventoryDto);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getInventory", summary = "Get inventory by ID")
    public InventoryDto get(@PathVariable Integer id) {
        return inventoryService.get(id);
    }

    @PutMapping("/{id}")
    @Operation(operationId = "updateInventory", summary = "Rename inventory")
    public InventoryDto update(@PathVariable Integer id, @Valid @RequestBody InventoryDto inventoryDto) {
        return inventoryService.update(id, inventoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "deleteInventory", summary = "Delete inventory and its products")
    public void delete(@PathVariable Integer id) {
        inventoryService.delete(id);
    }
}
