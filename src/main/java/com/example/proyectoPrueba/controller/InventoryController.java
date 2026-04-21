package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventoryDTO;
import com.example.proyectoPrueba.model.Inventory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @GetMapping("/inventory")
    public InventoryDTO getAll(){
        return ;
    }
}
