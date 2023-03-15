package com.codegym.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OderDetail implements Serializable{
    @Id
   Long id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "p_id")
    Product product;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "o_ID")
    Order order;

    Long odQTY;

    public OderDetail() {
    }

    public OderDetail(Long id, Product product, Order order, Long odQTY) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.odQTY = odQTY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getOdQTY() {
        return odQTY;
    }

    public void setOdQTY(Long odQTY) {
        this.odQTY = odQTY;
    }
}