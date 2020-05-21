package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String email;
    private String name;
    private String password;
    private Boolean seller;
    private int age;
    private long phoneNumber;
    private Mailing mailing;

    public User() {
    }


    public User(@JsonProperty("email") String email,
                @JsonProperty("name") String name,
                @JsonProperty("password") String password,
                @JsonProperty("age") int age,
                @JsonProperty("deliveryAdd") String deliveryAdd,
                @JsonProperty("phoneNumber") long phoneNumber,
                @JsonProperty("mailing") Mailing mailing
                ) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.seller = false;
        this.age = age;
        this.phoneNumber=phoneNumber;
        this.mailing= mailing;
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

    public Mailing getMailing() {
        return mailing;
    }

    public void setMailing(Mailing mailing) {
        this.mailing = mailing;
    }

    public User setEmail(String email) {
        this.email = email;
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
