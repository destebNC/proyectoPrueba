package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Mapper mapper;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository,
                          InventoryRepository inventoryRepository,
                          Mapper mapper) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::productToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getById(Integer productId, Integer inventoryId) {

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        Product product = inventory.getProductList().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado en este inventario"
                ));

        return mapper.productToDto(product);
    }

    public void delete(Integer inventoryId, Integer productId){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (product.getInventory() == null ||
                !product.getInventory().getId().equals(inventoryId)) {
            throw new RuntimeException("Ese producto no pertenece a ese inventario");
        }

        productRepository.delete(product);
    }

    public void updateProduct(Integer inventoryId, Integer productId, Product newProduct){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (product.getInventory() == null ||
                !product.getInventory().getId().equals(inventoryId)) {
            throw new RuntimeException("Ese producto no pertenece a ese inventario");
        }

        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setWeight(newProduct.getWeight());

        productRepository.save(product);
    }

    public void addProduct(Integer inventoryId, ProductDto productDto){

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        Product product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setWeight(productDto.weight());

        product.setInventory(inventory);

        productRepository.save(product);
    }
}