package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offer {

    private String userEmail;
    private String productName;
    private int presentation;
    private double pricePresentation;
    private int minQuantity;
    private String description;


    public Offer(){

    }

    public Offer(@JsonProperty("productName")String productName,
                 @JsonProperty("userEmail")String userEmail,
                 @JsonProperty("presentation")int presentation,
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
                ", presentation='" + Integer.toString(presentation) + '\'' +
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

    public int getPresentation() {
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

    public Offer setPresentation(int presentation) {
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
    public Offer setDescription (String description) {
        this.description = description;
        return this;
    }

}
