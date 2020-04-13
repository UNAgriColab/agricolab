package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String email;
    private String name;
    private String password;
    private Boolean seller;
    private int age;

    public User() {
    }

    public User(@JsonProperty("email") String email,
                @JsonProperty("name") String name,
                @JsonProperty("password") String password,
                @JsonProperty("age") int age) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.seller = false;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            "vendor = " + Boolean.toString(seller) + '\'' +
            '}';
    }

    public String getEmail() {
        return email;
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
