package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final Mapper mapper; // 1. Añadimos el Mapper

    // Inyecciones
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository, Mapper mapper) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.mapper = mapper; // 2. Lo inyectamos en el constructor
    }

    // guardar un inventario
    public void save(InventoryDto inventoryDto) {
        Inventory inventory = mapper.dtoToInventory(inventoryDto);
        inventoryRepository.save(inventory);
    }

    // mostrar todos los productos (Sin paginar, por si lo necesitas en otro lado)
    public List<InventoryDto> getAll() {
        return inventoryRepository.findAll().stream()
                .map(mapper::inventoryToDto) // <-- SOLUCIONADO: Usamos el traductor
                .toList();
    }

    public Page<InventoryDto> getInventoryPaginated(int page, int size) {
        // 1. Creamos el objeto de paginación
        Pageable pageable = PageRequest.of(page, size);

        // 2. Traemos la página de la base de datos
        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);

        // 3. SOLUCIONADO: Mapeamos cada inventario a DTO usando el Mapper
        return inventoryPage.map(mapper::inventoryToDto);
    }

    // borrar un inventario
    public void delete(Integer inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }

    // actualizar un inventario
    public void updateInv(Integer inventoryId, Inventory newInventory){
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new RuntimeException("Inventario de ID " + inventoryId + " no encontrado"));

        inventory.setName(newInventory.getName());
        inventoryRepository.save(inventory);
    }

    // buscar por ID
    public InventoryDto getById(Integer inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        // SOLUCIONADO: Devolvemos el DTO traducido directamente
        return mapper.inventoryToDto(inventory);
    }
}