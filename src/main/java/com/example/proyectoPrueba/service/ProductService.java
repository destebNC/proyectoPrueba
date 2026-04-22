package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository=inventoryRepository;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ProductDto getById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return convertToDto(product);
    }
    public void delete(Integer inventoryId, Integer productId) {
        Inventory inventory=inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new RuntimeException(("Inventario con ID "+inventoryId+ " no encontrado")));

        Product product=inventory.getProductList().stream()
                .filter(p->p.getId().equals(productId))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Producto con ID "+productId+ " en el inventario de ID "+inventoryId+ " no ha sido encontrado"));

        inventory.getProductList().remove(product);
        inventoryRepository.save(inventory);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setWeight(product.getWeight());
        return dto;
    }

    public void updateProduct(Integer inventoryId, Integer productId, Product newProduct){
        //buscar inventario por id
        Inventory inventory=inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new RuntimeException("Inventario con ID " +inventoryId+ " no encontrado"));
        //buscar el producto por id
        Product product=inventory.getProductList().stream()
                .filter(p->p.getId().equals(productId))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Producto con ID " +productId+ " en el inventario de ID "+inventoryId+ " no ha sido encontrado"));

        // guardar nuevos datos
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setWeight(newProduct.getWeight());

        inventoryRepository.save(inventory);
    }

    public void addProduct(Integer inventoryId, Product product){
        Inventory inventory=inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new RuntimeException("No se ha podido encontrar el inventario de ID "+inventoryId));

        Product savedProduct= productRepository.save(product);
        inventory.getProductList().add(savedProduct);
        inventoryRepository.save(inventory);
    }
}