package com.codegym.model;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;


@Table(name = "categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = { @JoinColumn(name = "category_id") },
            inverseJoinColumns = {@JoinColumn(name = "product_id") })
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
