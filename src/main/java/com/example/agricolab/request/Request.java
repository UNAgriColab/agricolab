package com.example.agricolab.request;

import com.example.agricolab.users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.internal.$Gson$Preconditions;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Request {

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
    private int unit;

    @Column
    private int numberOfUnits;

    @Column
    private double TotalPrice;

    public Request(){

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

    public int getUnit() {
        return unit;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public double getTotalPrice() {
        return TotalPrice;
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

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }
}
