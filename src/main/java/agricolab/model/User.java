package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String email;
    private String name;
    private String password;
    private Boolean seller;
    private int age;
    private String deliveryAdd;
    private long phoneNumber;

    public User() {
    }


    public User(@JsonProperty("email") String email,
                @JsonProperty("name") String name,
                @JsonProperty("password") String password,
                @JsonProperty("age") int age,
                @JsonProperty("deliveryAdd") String deliveryAdd,
                @JsonProperty("phoneNumber") long phoneNumber
                ) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.seller = false;
        this.age = age;
        this.phoneNumber=phoneNumber;
        this.deliveryAdd = deliveryAdd;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            "vendor = " + seller + '\'' +
            '}';
    }

    public String getEmail() {
        return email;
    }

    public String getDeliveryAdd() {
        return deliveryAdd;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getSeller() {
        return seller;
    }

    public int getAge() {
        return age;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setDeliveryAdd(String deliveryAdd) {
        this.deliveryAdd = deliveryAdd;
        return this;
    }

    public User setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setSeller(Boolean seller) {
        this.seller = seller;
        return this;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }
}
