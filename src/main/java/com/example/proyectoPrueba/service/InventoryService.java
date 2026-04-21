package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
import com.example.proyectoPrueba.repository.InventoryRepository;
import com.example.proyectoPrueba.repository.ProductRepository;

import java.util.List;

public class InventoryService {
    private final Mapper mapper;
    private final InventoryRepository repository;

    pubic InventoryService(Mapper mapper, ProductRepository repository){
        this.mapper=mapper;
        this.repository=repository;
    }

    public InventoryDto inventoryToInventoryDto(Inventory inventory){
        return mapper.inventoryToDto(inventory)
    }

    public List<InventoryDto> getAll(){
        return repository.findAll()
                .stream()
                .map(mapper)
    }
}
