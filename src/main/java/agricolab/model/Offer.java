package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offer {

    private int id;
    private String sellerEmail;
    private String productName;
    private int presentation;
    private double pricePresentation;
    private int minQuantity;
    private String description;
    private Boolean state;
    private double qualification;
    private int numberOfReviews;


    public Offer() {

    }

    public Offer(@JsonProperty("productName") String productName,
                 @JsonProperty("sellerEmail") String sellerEmail,
                 @JsonProperty("presentation") int presentation,
                 @JsonProperty("pricePresentation") double pricePresentation,
                 @JsonProperty("minQuantity") int minQuantity,
                 @JsonProperty("description") String description
    ) {
        this.productName = productName;
        this.sellerEmail = sellerEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
        this.state = true;
        this.qualification = 0;
        this.numberOfReviews = 0;
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
            ", numberOfReviews=" + numberOfReviews +
            '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    public double getPricePresentation() {
        return pricePresentation;
    }

    public void setPricePresentation(double pricePresentation) {
        this.pricePresentation = pricePresentation;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public double getQualification() {
        return qualification;
    }

    public void setQualification(double qualification) {
        this.qualification = qualification;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
}
