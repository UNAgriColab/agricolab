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
    }

    public void manual(String sellerEmail, String productName, int presentation, double pricePresentation, int minQuantity, String description) {

        this.productName = productName;
        this.sellerEmail = sellerEmail;
        this.presentation = presentation;
        this.pricePresentation = pricePresentation;
        this.minQuantity = minQuantity;
        this.description = description;
        this.state = true;
    }

    @Override
    public String toString() {
        return "Offer{" +
            ", sellerEmail='" + sellerEmail + '\'' +
            ", name='" + productName + '\'' +
            ", presentation='" + presentation + '\'' +
            ", pricePresentation='" + pricePresentation + '\'' +
            ", minQuantity='" + minQuantity + '\'' +
            '}';
    }


    public String getId() {
        return id;
    }

    public Offer setId(String id) {
        this.id = id;
        return this;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public Offer setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Offer setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getPresentation() {
        return presentation;
    }

    public Offer setPresentation(int presentation) {
        this.presentation = presentation;
        return this;
    }

    public double getPricePresentation() {
        return pricePresentation;
    }

    public Offer setPricePresentation(double pricePresentation) {
        this.pricePresentation = pricePresentation;
        return this;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public Offer setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public Offer setState(Boolean state) {
        this.state = state;
        return this;
    }

}
