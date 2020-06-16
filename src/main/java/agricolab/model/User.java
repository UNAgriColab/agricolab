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
    private double qualification;
    private int numberOfReviews;
    private int numberOfOrdersDone;
    private int numberOfOrdersRecieved;

    public User() {
    }

    public User(@JsonProperty("email") String email,
                @JsonProperty("name") String name,
                @JsonProperty("password") String password,
                @JsonProperty("age") int age,
                @JsonProperty("phoneNumber") long phoneNumber
    ) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.seller = false;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.mailing = new Mailing();
        this.qualification = 0;
        this.numberOfReviews = 0;
        this.numberOfOrdersDone = 0;
        this.numberOfOrdersRecieved = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", seller=" + seller +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", mailing=" + mailing +
                ", qualification=" + qualification +
                ", numberOfReviews=" + numberOfReviews +
                ", numberOfOrdersdone=" + numberOfOrdersDone +
                ", numberOfOrdersrecieved=" + numberOfOrdersRecieved +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Mailing getMailing() {
        return mailing;
    }

    public void setMailing(Mailing mailing) {
        this.mailing = mailing;
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

    public int getNumberOfOrdersDone() {
        return numberOfOrdersDone;
    }

    public void setNumberOfOrdersDone(int numberOfOrdersDone) {
        this.numberOfOrdersDone = numberOfOrdersDone;
    }

    public int getNumberOfOrdersRecieved() {
        return numberOfOrdersRecieved;
    }

    public void setNumberOfOrdersRecieved(int numberOfOrdersRecieved) {
        this.numberOfOrdersRecieved = numberOfOrdersRecieved;
    }
}
