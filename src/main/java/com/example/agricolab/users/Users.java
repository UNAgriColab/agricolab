package com.example.agricolab.users;

import com.example.agricolab.request.Request;
import org.attoparser.trace.MarkupTraceEvent;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String full_name;

    @Column
    private Date birth;

    @Column
    private String email;

    @Column
    private String password;

    public Users() {
    }


    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public Date getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
