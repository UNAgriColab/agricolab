package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;

import javax.persistence.*;
import java.util.UUID;


public class Request {



    private String userEmail;
    private String productName;
    private int unit;
    private int numberOfUnits;
    private double totalPrice;
    private String description;


    public Request(){

    }

    public Request( @JsonProperty("productName")String productName,
                    @JsonProperty("userEmail")String userEmail,
                    @JsonProperty("unit")int unit,
                    @JsonProperty("numberOfUnits")int numberOfUnits,
                    @JsonProperty("totalPrice")double totalPrice,
                    @JsonProperty("description")String description) {

        this.userEmail = userEmail;
        this.productName = productName;
        this.unit = unit;
        this.numberOfUnits = numberOfUnits;
        this.totalPrice = totalPrice;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Request{" +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + productName + '\'' +
                ", unit='" + Integer.toString(unit) + '\'' +
                ", numberOfUnits='" + Integer.toString(numberOfUnits)  + '\'' +
                ", totalPrice='" + Double.toString(totalPrice) + '\'' +
                '}';
    }

    public String getUserEmail(){return userEmail; }

    public String getProductName() {
        return productName;
    }

    public int getUnit() {
        return unit;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public double getTotalPrice() { return totalPrice; }

    public String getDescription() {return description;}


    public Request setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Request setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Request setUnit(int unit) {
        this.unit = unit;
        return this;
    }

    public Request setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
        return this;
    }

    public Request setTotalPrice (double totalPrice){
        this.totalPrice = totalPrice;
        return this;
    }
    public Request setDescription (String description){
        this.description = description;
        return this;
    }

}
