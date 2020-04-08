package com.example.agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offers {

    private int id;

    private String productName;

    private int convention;

    private double priceConvention;

    private int mainQuantity;

    private String description;

    public Offers(){

    }

    public Offers( @JsonProperty("productName")String productName,
                   @JsonProperty("convention")int convention,
                   @JsonProperty("priceConvention")double priceConvention,
                   @JsonProperty("mainQuantity")int mainQuantity,
                   @JsonProperty("description")String description) {
        this.productName = productName;
        this.convention = convention;
        this.priceConvention = priceConvention;
        this.mainQuantity = mainQuantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", convention='" + convention + '\'' +
                ", priceConvention='" + priceConvention + '\'' +
                ", mainQuantity='" + mainQuantity + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getConvention() {
        return convention;
    }

    public double getPriceConvention() {
        return priceConvention;
    }

    public int getMainQuantity() {
        return mainQuantity;
    }

    public String getDescription() { return description; }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setConvention(int convention) {
        this.convention = convention;
    }

    public void setPriceConvention(double priceConvention) {
        priceConvention = priceConvention;
    }

    public void setMainQuantity(int mainQuantity) {
        this.mainQuantity = mainQuantity;
    }

    public void setDescription(String description) { this.description = description; }
}
