package com.destebnc.supermercado.service;

import com.destebnc.supermercado.dto.InventoryDto;
import com.destebnc.supermercado.exception.ResourceNotFoundException;
import com.destebnc.supermercado.model.Inventory;
import com.destebnc.supermercado.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final Mapper mapper;

    public InventoryService(InventoryRepository inventoryRepository, Mapper mapper) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
    }

    public Page<InventoryDto> list(int page, int size) {
        return inventoryRepository.findAll(Paging.of(page, size)).map(mapper::inventoryToDto);
    }

    /** Crea un inventario (opcionalmente con sus productos). */
    @Transactional
    public InventoryDto create(InventoryDto inventoryDto) {
        Inventory saved = inventoryRepository.save(mapper.dtoToInventory(inventoryDto));
        return mapper.inventoryToDto(saved);
    }

    public InventoryDto get(Integer inventoryId) {
        return mapper.inventoryToDto(findInventory(inventoryId));
    }

    /** Solo cambia el nombre; los productos se gestionan desde /api/inventories/{id}/products. */
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
