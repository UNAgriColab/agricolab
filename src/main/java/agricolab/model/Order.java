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

    public int getState(){ return state;    }

    public String getBuyerEmail(){return buyerEmail; }

    public int getUnit() { return unit; }

    public String getSellerEmail() { return sellerEmail;    }

    public String getProductName() { return productName;    }

    public int getNumberOfUnits() { return numberOfUnits; }

    public double getTotalPrice() { return totalPrice; }

    public String getDescription() {return description;}

    public String getOfferReference(){return offerReference;}

    public String getId(){return  id;}

    public Order setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
        return this;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOfferReference(String offerReference) {
        this.offerReference = offerReference;
    }

    public Order setState (int state){
        this.state = state;
        return this;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public Order setUnit(int unit) {
        this.unit = unit;
        return this;
    }

    public Order setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
        return this;
    }

    public Order setTotalPrice (double totalPrice){
        this.totalPrice = totalPrice;
        return this;
    }

    public Order setDescription (String description){
        this.description = description;
        return this;
    }

    public Order setOfferRefference (String offerReference){
        this.offerReference = offerReference;
        return this;
    }

}
