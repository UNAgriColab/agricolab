package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String email;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private Boolean vendedor;

    public User() {
    }

    public User(@JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password,
                @JsonProperty("vendedor")Boolean vendedor) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            "vendedor = " + Boolean.toString(vendedor) +'\'' +
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

    public Boolean getVendedor() {
        return vendedor;
    }

    public User setEmail(String email) {
        this.email = email;
        return this ;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setVendedor(Boolean vendedor) {
        this.vendedor = vendedor;
        return this;
    }
}
