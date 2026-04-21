package com.example.proyectoPrueba.dto;

public class ProductDto {
    private Integer id;
    private String name;
    private float price;
    private float weight;

    public ProductDto() {
    }

    public ProductDto(String name, float price, float weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }
}