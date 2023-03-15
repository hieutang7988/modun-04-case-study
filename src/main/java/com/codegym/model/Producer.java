package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "producers")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="Name producer is not empty")
    @Column(name = "name_producer")
    private String nameProducer;

    @OneToMany(mappedBy = "producer")
//    private List<Product> products;
    private Set<Product> products = new HashSet<>();

    public Producer() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProducer() {
        return nameProducer;
    }

    public void setNameProducer(String nameProducer) {
        this.nameProducer = nameProducer;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", nameProducer='" + nameProducer + '\'' +
                ", products=" + products +
                '}';
    }
}
