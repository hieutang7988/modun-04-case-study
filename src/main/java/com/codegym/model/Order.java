package com.codegym.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oID;
    private Long cID;
    private Date oDate;
    private float oTotalPrice;

//    @ManyToMany(mappedBy = "orders")
//    Set<Product> products;

    @OneToMany(mappedBy = "order")
    Set<OderDetail> oderDetails;

    public Order() {
    }

    public Order(Long oID, Long cID, Date oDate, float oTotalPrice, Set<OderDetail> oderDetails) {
        this.oID = oID;
        this.cID = cID;
        this.oDate = oDate;
        this.oTotalPrice = oTotalPrice;
        this.oderDetails = oderDetails;
    }

    public Long getoID() {
        return oID;
    }

    public void setoID(Long oID) {
        this.oID = oID;
    }

    public Long getcID() {
        return cID;
    }

    public void setcID(Long cID) {
        this.cID = cID;
    }

    public Date getoDate() {
        return oDate;
    }

    public void setoDate(Date oDate) {
        this.oDate = oDate;
    }

    public float getoTotalPrice() {
        return oTotalPrice;
    }

    public void setoTotalPrice(float oTotalPrice) {
        this.oTotalPrice = oTotalPrice;
    }

    public Set<OderDetail> getOderDetails() {
        return oderDetails;
    }

    public void setOderDetails(Set<OderDetail> oderDetails) {
        this.oderDetails = oderDetails;
    }
}
