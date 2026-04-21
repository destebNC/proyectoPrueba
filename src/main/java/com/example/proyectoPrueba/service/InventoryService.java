package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDTO;
import com.example.proyectoPrueba.model.Inventory;
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

    public InventoryDTO inventoryToInventoryDto(Inventory inventory){
        return mapper.inventoryToDto(inventory);
    }

    public List<InventoryDTO> getAll(){
        return repository.findAll()
                .stream()
                .map(mapper::inventoryToDto)
                .toList();
    }
}
