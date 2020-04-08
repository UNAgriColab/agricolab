package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    private int id;

    private String productName;

    private int unit;

    private int numberOfUnits;

    private double totalPrice;

    public Request(){

    }

    public Request( @JsonProperty("productName")String productName,
                    @JsonProperty("unit")int unit,
                    @JsonProperty("numberOfUnits")int numberOfUnits,
                    @JsonProperty("totalPrice")double totalPrice) {
        this.productName = productName;
        this.unit = unit;
        this.numberOfUnits = numberOfUnits;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", unit='" + unit + '\'' +
                ", numberOfUnits='" + numberOfUnits + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnit() {
        return unit;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public void setTotalPrice(double totalPrice) {
        totalPrice = totalPrice;
    }
}
