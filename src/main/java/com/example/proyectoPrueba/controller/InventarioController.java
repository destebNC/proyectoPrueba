package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventarioDTO;
import com.example.proyectoPrueba.dto.ProductoDto;
import com.example.proyectoPrueba.model.Inventario;
import com.example.proyectoPrueba.model.Producto;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventarioController {

    private final ProductService productService;

    public InventarioController (ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/productos")
    public List<ProductoDto> getAll(){
        return productService.getAll();
    }

    //GET BY ID
    @GetMapping("/productos/{product_id}")
    public ProductoDto getById(
            @PathVariable("product_id") Integer id
    ){
        return productService.getById(id);
    }

    @PostMapping("/agregar")
    public String post(
            @RequestBody Producto producto
            ){
            productService.save(producto);
            return "Producto guardado con éxito";
    }

    @DeleteMapping("/delete/{product_id}")
    public String delete(
            @PathVariable("product_id") Integer id
    ){
        productService.delete(id);
        return "El producto con id: "+id+" ha sido eliminado con éxito";
    }

}
