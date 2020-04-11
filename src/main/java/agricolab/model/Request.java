package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Request {

    @Id
    private long id;
    @Column
    private String userEmail;
    @Column
    private String productName;
    @Column
    private int unit;
    @Column
    private int numberOfUnits;
    @Column
    private double totalPrice;
    @Column
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
        return "User{" +
                "id=" + Long.toString(id) +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + productName + '\'' +
                ", unit='" + Integer.toString(unit) + '\'' +
                ", numberOfUnits='" + Integer.toString(numberOfUnits)  + '\'' +
                ", totalPrice='" + Double.toString(totalPrice) + '\'' +
                '}';
    }

    public long getId() {
        return id;
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

    public Request setId(int id) {
        this.id = id;
        return this;
    }


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
