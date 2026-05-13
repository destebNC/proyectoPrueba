package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import com.example.proyectoPrueba.service.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
<<<<<<< HEAD

    private final ProductRepository productRepository;

    // Inyectamos el repositorio que creamos en el paso anterior
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
=======

    private final Mapper mapper;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, InventoryRepository inventoryRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.inventoryRepository=inventoryRepository;
        this.mapper=mapper;
>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53
    }

    /**
     * Obtiene todos los productos de la base de datos y los convierte a DTO.
     */
    public List<ProductDto> getAll() {
<<<<<<< HEAD
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * Busca un producto por ID. Si no lo encuentra, lanza una excepción
     * para que el GlobalExceptionHandler la capture.
     */
    public ProductDto getById(Integer id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getName(), product.getPrice()))
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no existe en la base de datos"));
    }

    /**
     * Guarda el producto físicamente en la base de datos MySQL.
     */
    public void save(Product product) {
        productRepository.save(product);
    }

    /**
     * Elimina el producto de la base de datos por su ID.
     */
    public void delete(Integer id) {
        productRepository.deleteById(id);
=======
        return productRepository.findAll().stream().map(mapper::productToDto).collect(Collectors.toList());
    }

    // 1. Paginar TODOS los productos de la base de datos de golpe
    public Page<ProductDto> getAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(mapper::productToDto);
    }

    // 2. Paginar SOLO los productos que pertenecen a un inventario concreto
    public Page<ProductDto> getProductsByInventoryPaginated(Integer inventoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByInventoryId(inventoryId, pageable);
        return productPage.map(mapper::productToDto);
    }

    public ProductDto getById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return mapper.productToDto(product);
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
>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53
    }
}