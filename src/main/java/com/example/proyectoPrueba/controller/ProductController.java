package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.data.domain.Page; // <-- Importación necesaria para la paginación
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    // --- MÉTODOS ORIGINALES ---

    @GetMapping
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{product_id}")
    public ProductDto getById(@PathVariable("product_id") Integer id){
        return productService.getById(id);
    }

    @PostMapping("/{inventory_id}")
    public String addProductToInventory(
            @PathVariable("inventory_id") Integer inventoryId,
            @RequestBody Product product) {
        productService.addProduct(inventoryId, product);
        return "Producto añadido al inventario con éxito";
    }

    @DeleteMapping("/{inventory_id}/{product_id}")
    public String delete(
            @PathVariable("product_id") Integer productId,
            @PathVariable("inventory_id") Integer inventoryId){
        productService.delete(inventoryId, productId);
        return "El producto con id: "+productId+" ha sido eliminado con éxito del inventario de ID " +inventoryId;
    }

    @PutMapping("/{inventory_id}/{product_id}")
    public String update(
            @PathVariable("inventory_id") Integer inventoryId,
            @PathVariable("product_id") Integer productId,
            @RequestBody Product product){
        productService.updateProduct(inventoryId,productId, product);
        return "Producto actualizado con éxito";
    }

    // --- NUEVOS MÉTODOS DE PAGINACIÓN ---

    // 1. Obtener TODOS los productos paginados
    @GetMapping("/paginated")
    public Page<ProductDto> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getAllPaginated(page, size);
    }

    // 2. Obtener productos paginados de un INVENTARIO específico
    @GetMapping("/{inventory_id}/paginated")
    public Page<ProductDto> getProductsByInventoryPaginated(
            @PathVariable("inventory_id") Integer inventoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getProductsByInventoryPaginated(inventoryId, page, size);
    }
}