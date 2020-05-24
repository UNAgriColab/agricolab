package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offer {

    private String id;
    private String SellerEmail;
    private String productName;
    private int presentation;
    private double pricePresentation;
    private int minQuantity;
    private String description;
    private Boolean state;


    public Offer(){

    }

    public void manual (String userEmail, String productName, int presentation, double pricePresentation , int minQuantity , String description) {

        this.productName = productName;
        this.SellerEmail = userEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
        this.state = true;
    }

    public Offer(@JsonProperty("productName")String productName,
                 @JsonProperty("userEmail")String userEmail,
                 @JsonProperty("presentation")int presentation,
                 @JsonProperty("pricePresentation")double pricePresentation,
                 @JsonProperty("minQuantity")int minQuantity,
                 @JsonProperty("description")String description
    ){
        this.productName = productName;
        this.SellerEmail = userEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
        this.state = true;
    }

    @Override
    public String toString() {
        return "Offer{" +
                ", userEmail='" + SellerEmail + '\'' +
                ", name='" + productName + '\'' +
                ", presentation='" + presentation + '\'' +
                ", pricePresentation='" + pricePresentation + '\'' +
                ", minQuantity='" + minQuantity + '\'' +
                '}';
    }


    public String getId(){return  id;}

    public String getSellerEmail() {
        return SellerEmail;
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

    public Boolean getState(){ return state; }

    public Offer setState(Boolean state){
        this.state = state;
        return this;
    }

    public Offer setId(String id) {
        this.id = id;
        return this;
    }

    public Offer setSellerEmail(String sellerEmail) {
        this.SellerEmail = sellerEmail;
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
