package com.example.agricolab.offers;

import com.example.agricolab.users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name= "Users_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Users user;

    @Column
    private String productName;

    @Column
    private int convention;

    @Column
    private double PriceConvention;

    @Column
    private int mainQuantity;

    public Offers(){

    }

    public int getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public String getProductName() {
        return productName;
    }

    public int getConvention() {
        return convention;
    }

    public double getPriceConvention() {
        return PriceConvention;
    }

    public int getMainQuantity() {
        return mainQuantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setConvention(int convention) {
        this.convention = convention;
    }

    public void setPriceConvention(double priceConvention) {
        PriceConvention = priceConvention;
    }

    public void setMainQuantity(int mainQuantity) {
        this.mainQuantity = mainQuantity;
    }
}
