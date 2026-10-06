package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.exception.ResourceNotFoundException;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final Mapper mapper;

    public ProductService(ProductRepository productRepository, InventoryRepository inventoryRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll(Sort.by("id")).stream()
                .map(mapper::productToDto)
                .toList();
    }

    public Page<ProductDto> getAllPaginated(int page, int size) {
        return productRepository.findAll(Paging.of(page, size)).map(mapper::productToDto);
    }

    public ProductDto getById(Integer productId) {
        return productRepository.findById(productId)
                .map(mapper::productToDto)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + productId + " no existe"));
    }

    /** Crea un producto suelto, sin inventario. */
    @Transactional
    public ProductDto create(ProductDto productDto) {
        return mapper.productToDto(productRepository.save(mapper.dtoToProduct(productDto)));
    }

    @Transactional
    public ProductDto addToInventory(Integer inventoryId, ProductDto productDto) {
        Inventory inventory = findInventory(inventoryId);
        Product product = mapper.dtoToProduct(productDto);
        inventory.addProduct(product);
        return mapper.productToDto(productRepository.save(product));
    }

    public Page<ProductDto> getByInventoryPaginated(Integer inventoryId, int page, int size) {
        findInventory(inventoryId);
        return productRepository.findByInventoryId(inventoryId, Paging.of(page, size)).map(mapper::productToDto);
    }

    @Transactional
    public ProductDto update(Integer inventoryId, Integer productId, ProductDto productDto) {
        Product product = findProductInInventory(inventoryId, productId);
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setWeight(productDto.weight());
        return mapper.productToDto(product);
    }

    @Transactional
    public void delete(Integer inventoryId, Integer productId) {
        Product product = findProductInInventory(inventoryId, productId);
        product.getInventory().getProductList().remove(product);
        productRepository.delete(product);
    }

    private Inventory findInventory(Integer inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario con ID " + inventoryId + " no encontrado"));
    }

    private Product findProductInInventory(Integer inventoryId, Integer productId) {
        findInventory(inventoryId);
        return productRepository.findByIdAndInventoryId(productId, inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El producto con ID " + productId + " no existe en el inventario " + inventoryId));
    }
}
