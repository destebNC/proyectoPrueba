package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private Mapper mapper;
    private InventoryRepository repository;

    public InventoryService(Mapper mapper, InventoryRepository repository){
        this.mapper=mapper;
        this.repository=repository;
    }

    public InventoryDto inventoryToInventoryDto(Inventory inventory){
        return mapper.inventoryToDto(inventory);
    }

    public InventoryDto getById(Integer id){
        Inventory inventory = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        return mapper.inventoryToDto(inventory);
    }

    public List<InventoryDto> getAll(){
        return repository.findAll()
                .stream()
                .map(mapper::inventoryToDto)
                .toList();
    }

    public Inventory save(Inventory inventory){
        return repository.save(inventory);
    }

    public void addProduct(Integer inventoryId, Product product) {

        Inventory inventory = InventoryRepository.getById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        // Guardas el producto primero (si usas JPA)
        ProductService savedProduct = savedProduct.save(product);

        // Añades al inventario
        inventory.getProductList().add(savedProduct);
    }
}
