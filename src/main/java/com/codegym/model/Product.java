package com.codegym.model;



import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private  String material;
    private double weight;



    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = {@JoinColumn(name = "category_id") })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Category> categories = new HashSet<>();
    @Column(name = "image")
    private String image;
//    @ManyToMany
//    @JoinTable(
//            name = "orderDetail",
//            joinColumns = @JoinColumn(name = "p_ID"),
//            inverseJoinColumns = @JoinColumn(name = "o_ID"))
//            Set<Order> orders;

//    @OneToMany(mappedBy = "product")
//    Set<OderDetail> oderDetails;

    public Product() {
    }

    public Product(String name, double price, String description, String material, double weight, Producer producer, Set<Category> categories, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.material = material;
        this.weight = weight;
        this.producer = producer;
        this.categories = categories;
        this.image = image;
    }

    public Product(Long id, String name, double price, String description, String material, double weight, Producer producer, Set<Category> categories, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}

