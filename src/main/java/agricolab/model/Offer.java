package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offer {

    private String id;
    private String sellerEmail;
    private String productName;
    private int presentation;
    private double pricePresentation;
    private int minQuantity;
    private String description;
    private Boolean state;
    private int qualification;


    public Offer() {

    }

    public void manual (String sellerEmail, String productName, int presentation, double pricePresentation , int minQuantity , String description) {

        this.productName = productName;
        this.sellerEmail = sellerEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
        this.state = true;
    }

    public Offer(@JsonProperty("productName")String productName,
                 @JsonProperty("sellerEmail")String sellerEmail,
                 @JsonProperty("presentation")int presentation,
                 @JsonProperty("pricePresentation")double pricePresentation,
                 @JsonProperty("minQuantity")int minQuantity,
                 @JsonProperty("description")String description
    ){
        this.productName = productName;
        this.sellerEmail = sellerEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
        this.state = true;
        this.qualification = 0;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", productName='" + productName + '\'' +
                ", presentation=" + presentation +
                ", pricePresentation=" + pricePresentation +
                ", minQuantity=" + minQuantity +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", qualification=" + qualification +
                '}';
    }

    public String getId(){return  id;}

    public String getSellerEmail() {
        return sellerEmail;
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

    public int getQualification(){ return qualification; }

    public Offer setState(Boolean state){
        this.state = state;
        return this;
    }

    public Offer setId(String id) {
        this.id = id;
        return this;
    }

    public Offer setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
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


    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Offer setQualification(int qualification){
        this.qualification = qualification;
        return this;
    }

}
