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

    // Inyecciones
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    // guardar un inventario
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    // mostrar todos los productos (Sin paginar, por si lo necesitas en otro lado)
    public List<InventoryDto> getAll() {
        return inventoryRepository.findAll().stream()
                .map(inv -> {
                    InventoryDto dto = new InventoryDto();
                    dto.setId(inv.getId());
                    dto.setName(inv.getName());
                    dto.setProducts(inv.getProductList());
                    return dto;
                })
                .toList();
    }

    public Page<InventoryDto> getInventoryPaginated(int page, int size) {
        // 1. Creamos el objeto de paginación
        Pageable pageable = PageRequest.of(page, size);

        // 2. Traemos la página de la base de datos
        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);

        // 3. Mapeamos cada inventario a DTO usando tu misma lógica
        return inventoryPage.map(inv -> {
            InventoryDto dto = new InventoryDto();
            dto.setId(inv.getId());
            dto.setName(inv.getName());
            dto.setProducts(inv.getProductList());
            return dto;
        });
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
        inventoryRepository.save(inventory); // Añado el save() que suele ser necesario para guardar el cambio
    }

    // buscar por ID
    public InventoryDto getById(Integer inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        InventoryDto dto = new InventoryDto();
        dto.setId(inventory.getId());
        dto.setName(inventory.getName());
        dto.setProducts(inventory.getProductList());

        return dto;
    }
}