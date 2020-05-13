package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offer {

    private String id;
    private String userEmail;
    private String productName;
    private double presentation;
    private double pricePresentation;
    private int minQuantity;
    private String description;


    public Offer(){

    }

    public Offer(@JsonProperty("productName")String productName,
                 @JsonProperty("userEmail")String userEmail,
                 @JsonProperty("presentation")double presentation,
                 @JsonProperty("pricePresentation")double pricePresentation,
                 @JsonProperty("minQuantity")int minQuantity,
                 @JsonProperty("description")String description){
        this.productName = productName;
        this.userEmail = userEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Offer{" +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + productName + '\'' +
                ", presentation='" + Double.toString(presentation) + '\'' +
                ", pricePresentation='" + Double.toString(pricePresentation) + '\'' +
                ", minQuantity='" + Integer.toString(minQuantity) + '\'' +
                '}';
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getProductName() {
        return productName;
    }

    public double getPresentation() {
        return presentation;
    }

    public double getPricePresentation() {
        return pricePresentation;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public String getDescription(){return description;}

    public Offer setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Offer setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Offer setPresentation(double presentation) {
        this.presentation = presentation;
        return this;
    }

    public Offer setPricePresentation(double pricePresentation) {
        this.pricePresentation = pricePresentation;
        return this;
    }

    public Offer setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
        return this;
    }
<<<<<<<<< Temporary merge branch 1:src/main/java/agricolab/model/Offer.java
=========

>>>>>>>>> Temporary merge branch 2:src/main/java/agricolab/model/Offers.java
    public Offer setDescription (String description) {
        this.description = description;
        return this;
    }

}
