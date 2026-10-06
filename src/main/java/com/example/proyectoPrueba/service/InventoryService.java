package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.exception.ResourceNotFoundException;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final Mapper mapper;

    public InventoryService(InventoryRepository inventoryRepository, Mapper mapper) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
    }

    /** Crea un inventario (opcionalmente con sus productos). */
    @Transactional
    public InventoryDto create(InventoryDto inventoryDto) {
        Inventory saved = inventoryRepository.save(mapper.dtoToInventory(inventoryDto));
        return mapper.inventoryToDto(saved);
    }

    public List<InventoryDto> getAll() {
        return inventoryRepository.findAll(Sort.by("id")).stream()
                .map(mapper::inventoryToDto)
                .toList();
    }

    public Page<InventoryDto> getPaginated(int page, int size) {
        return inventoryRepository.findAll(Paging.of(page, size)).map(mapper::inventoryToDto);
    }

    public InventoryDto getById(Integer inventoryId) {
        return mapper.inventoryToDto(findInventory(inventoryId));
    }

    /** Solo cambia el nombre; los productos se gestionan desde /api/productos/inv/{id}. */
    @Transactional
    public InventoryDto update(Integer inventoryId, InventoryDto inventoryDto) {
        Inventory inventory = findInventory(inventoryId);
        inventory.setName(inventoryDto.name());
        return mapper.inventoryToDto(inventory);
    }

    /** Borra el inventario y, en cascada, sus productos. */
    @Transactional
    public void delete(Integer inventoryId) {
        inventoryRepository.delete(findInventory(inventoryId));
    }

    private Inventory findInventory(Integer inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario con ID " + inventoryId + " no encontrado"));
    }
}
