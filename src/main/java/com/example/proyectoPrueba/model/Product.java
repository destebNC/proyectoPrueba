package com.example.proyectoPrueba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Map;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private float price;
    private float weight;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @JsonIgnore
    private Inventory inventory;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> attributes;

    public Product() {
    }

    public Product(String name, float price, float weight){
        this.name=name;
        this.price=price;
        this.weight=weight;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
}