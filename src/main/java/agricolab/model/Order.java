package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Order {

    private String id;
    private String userEmail;
    private String offerReference;
    private int unit;
    private int numberOfUnits;
    private double totalPrice;
    private String description;
    private int state;


    public Order(){

    }

    public void manual (String userEmail, String offerReference, int numberOfUnits, int unit , double totalPrice , String description) {

        this.userEmail = userEmail;
        this.offerReference = offerReference;
        this.state = 0;
        this.unit = unit;
        this.numberOfUnits = numberOfUnits;
        this.totalPrice = totalPrice;
        this.description = description;
    }

    public Order(   @JsonProperty("offerReference")String offerReference,
                    @JsonProperty("userEmail")String userEmail,
                    @JsonProperty("unit")int unit,
                    @JsonProperty("numberOfUnits")int numberOfUnits,
                    @JsonProperty("description")String description,
                    @JsonProperty("id")String id
                    ) {

        this.userEmail = userEmail;
        this.offerReference = offerReference;
        this.state = 0;
        this.unit = unit;
        this.numberOfUnits = numberOfUnits;
        this.totalPrice = numberOfUnits;
        this.description = description;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Order{" + "id "+ id +
                ", userEmail='" + userEmail + '\'' +
                ", unit='" + unit + '\'' +
                ", numberOfUnits='" + numberOfUnits + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }

    public int getState(){ return state;    }

    public String getUserEmail(){return userEmail; }

    public int getUnit() { return unit; }

    public int getNumberOfUnits() { return numberOfUnits; }

    public double getTotalPrice() { return totalPrice; }

    public String getDescription() {return description;}

    public String getOfferReference(){return offerReference;}

    public String getId(){return  id;}

    public Order setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
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
