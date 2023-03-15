package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

public class ProductForm {
    private Long id;
    private String name;
    private double price;
    private String description;
    private  String material;
    private double weight;
    private Producer producer;
    private Set<Category> categories = new HashSet<>();
    private MultipartFile image;


    public ProductForm() {
    }

    public ProductForm(Long id, String name, double price, String description, String material,
                       double weight, Producer producer, Set<Category> categories, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.material = material;
        this.weight = weight;
        this.producer = producer;
        this.categories = categories;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}