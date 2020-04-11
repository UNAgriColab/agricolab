package agricolab.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userEmail;
    @Column
    private String productName;
    @Column
    private int presentation;
    @Column
    private double pricePresentation;
    @Column
    private int minQuantity;
    @Column
    private String description;


    public Offers(){

    }

    public Offers( @JsonProperty("productName")String productName,
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
        return "User{" +
                "id=" + Long.toString(id) +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + productName + '\'' +
                ", presentation='" + Integer.toString(presentation) + '\'' +
                ", pricePresentation='" + Double.toString(pricePresentation) + '\'' +
                ", minQuantity='" + Integer.toString(minQuantity) + '\'' +
                '}';
    }

    public Long getId() {
        return id;
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

    public Offers setId(Long id) {
        this.id = id;
        return this;
    }

    public Offers setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Offers setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Offers setPresentation(int presentation) {
        this.presentation = presentation;
        return this;
    }

    public Offers setPricePresentation(double pricePresentation) {
        this.pricePresentation = pricePresentation;
        return this;
    }

    public Offers setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
        return this;
    }
    public Offers setDescription (String description) {
        this.description = description;
        return this;
    }

}
