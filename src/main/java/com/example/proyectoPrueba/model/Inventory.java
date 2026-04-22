package com.example.proyectoPrueba.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="inventarios")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList;

    public Inventory() {
    }

    public Inventory(String name, Integer id, List<Product> productList) {
        this.name = name;
        this.id = id;
        this.productList = productList;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
}