package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos") // Prefijo único para productos
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("/paginated")
    public Page<ProductDto> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getAllPaginated(page, size);
    }

    @GetMapping("/{product_id}")
    public ProductDto getById(@PathVariable("product_id") Integer id){
        return productService.getById(id);
    }

    @PostMapping
    public String addStandaloneProduct(@Valid @RequestBody Product product) {
        productService.save(product);
        return "Producto independiente guardado correctamente";
    }

    @PostMapping("/inv/{inventory_id}")
    public String addProductToInventory(
            @PathVariable("inventory_id") Integer inventoryId,
            @Valid @RequestBody Product product) {
        productService.addProduct(inventoryId, product);
        return "Producto añadido al inventario con éxito";
    }

    @PutMapping("/inv/{inventory_id}/{product_id}")
    public String update(
            @PathVariable("inventory_id") Integer inventoryId,
            @PathVariable("product_id") Integer productId,
            @Valid @RequestBody Product product){
        productService.updateProduct(inventoryId, productId, product);
        return "Producto actualizado con éxito";
    }

    @DeleteMapping("/inv/{inventory_id}/{product_id}")
    public String delete(
            @PathVariable("inventory_id") Integer inventoryId,
            @PathVariable("product_id") Integer productId){
        productService.delete(inventoryId, productId);
        return "El producto con id: " + productId + " ha sido eliminado con éxito del inventario " + inventoryId;
    }

    @GetMapping("/inv/{inventory_id}/paginated")
    public Page<ProductDto> getProductsByInventoryPaginated(
            @PathVariable("inventory_id") Integer inventoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.getProductsByInventoryPaginated(inventoryId, page, size);
    }
}