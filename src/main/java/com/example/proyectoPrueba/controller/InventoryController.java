package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.InventoryService;
import com.example.proyectoPrueba.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api") // Buena práctica: añadir un prefijo
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductService productService;

    public InventoryController(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    // --- ENDPOINTS DE PRODUCTOS ---
    @GetMapping("/productos")
    public List<ProductDto> listarProductos() {
        return productService.getAll();
    }

    @PostMapping("/productos")
    public ResponseEntity<String> agregarProducto(@Valid @RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok("Producto guardado");
    }

    // --- ENDPOINTS DE INVENTARIOS ---
    @PostMapping("/inv")
    public String create(@Valid @RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        return "Inventario creado";
    }

    @GetMapping("/inv/paginated")
    public Page<InventoryDto> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return inventoryService.getInventoryPaginated(page, size);
    }

    @DeleteMapping("/inv/{id}")
    public String deleteInv(@PathVariable Integer id) {
        inventoryService.delete(id);
        return "Inventario eliminado";
    }
}