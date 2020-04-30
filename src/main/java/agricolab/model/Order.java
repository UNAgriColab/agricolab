package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Order {

    private String ID;
    private String userEmail;
    private String offerReference;
    private String productName;
    private int unit;
    private int numberOfUnits;
    private double totalPrice;
    private String description;
    private Boolean state;


    public Order(){

    }

    public Order(   @JsonProperty("offerReference")String offerReference,
                    @JsonProperty("userEmail")String userEmail,
                    @JsonProperty("unit")int unit,
                    @JsonProperty("numberOfUnits")int numberOfUnits,
                    @JsonProperty("totalPrice")double totalPrice,
                    @JsonProperty("description")String description,
                    @JsonProperty("state") Boolean state) {

        this.userEmail = userEmail;
        this.offerReference = offerReference;
        this.state = state;
        this.unit = unit;
        this.numberOfUnits = numberOfUnits;
        this.totalPrice = totalPrice;
        this.description = description;
        this.ID=null;
    }

    @Override
    public String toString() {
        return "Order{" +
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

    public String getOfferReference(){return offerReference;}

    public Order setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Order setProductName(String productName) {
        this.productName = productName;
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
