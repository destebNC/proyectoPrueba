package com.example.proyectoPrueba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="inventarios")
public class Inventory {
    public String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> productList;
}
