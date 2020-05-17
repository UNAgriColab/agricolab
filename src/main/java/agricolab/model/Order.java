package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Order {

    private String id;
    private String sellerEmail;
    private String buyerEmail;
    private String productName;
    private String offerReference;
    private int unit;
    private int numberOfUnits;
    private double totalPrice;
    private String description;
    private int state;
    private String deliveryAdd;

    public Order(){}

    public Order(   @JsonProperty("offerReference")String offerReference,
                    @JsonProperty("buyerEmail")String buyerEmail,
                    @JsonProperty("numberOfUnits")int numberOfUnits,
                    @JsonProperty("description")String description
                    ) {

        this.buyerEmail = buyerEmail;
        this.offerReference = offerReference;
        this.numberOfUnits = numberOfUnits;
        this.description = description;
        this.state = 1;
    }

    @Override
    public String toString() {
        return "Order{" + "id "+ id +
                ", userEmail='" + buyerEmail + '\'' +
                ", unit='" + unit + '\'' +
                ", numberOfUnits='" + numberOfUnits + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOfferReference() {
        return offerReference;
    }

    public void setOfferReference(String offerReference) {
        this.offerReference = offerReference;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDeliveryAdd() {
        return deliveryAdd;
    }

    public void setDeliveryAdd(String deliveryAdd) {
        this.deliveryAdd = deliveryAdd;
    }
}
