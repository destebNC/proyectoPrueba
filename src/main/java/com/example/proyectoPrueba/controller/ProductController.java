package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("/products/{inventory_id}/{product_id}")
    public ProductDto getById(
            @PathVariable("product_id") Integer product_id,
            @PathVariable("inventory_id") Integer inventoryId){
        return productService.getById(product_id,inventoryId);
    }

    @PostMapping("/products/{inventory_id}")
    public String addProductToInventory(
            @PathVariable("inventory_id") Integer inventoryId,
            @RequestBody ProductDto product) {
        productService.addProduct(inventoryId, product);
        return "Producto añadido al inventario con éxito";
    }

    @DeleteMapping("/products/{inventory_id}/{product_id}")
    public String delete(
            @PathVariable("product_id") Integer productId,
            @PathVariable("inventory_id") Integer inventoryId){
        productService.delete(inventoryId, productId);
        return "El producto con id: "+productId+" ha sido eliminado con éxito del inventario de ID " +inventoryId;
    }

    @PutMapping("/products/{inventory_id}/{product_id}")
    public String update(
            @PathVariable("inventory_id") Integer inventoryId,
            @PathVariable("product_id") Integer productId,
            @RequestBody Product product){
        productService.updateProduct(inventoryId,productId, product);
        return "Producto actualizado con éxito";
    }
}