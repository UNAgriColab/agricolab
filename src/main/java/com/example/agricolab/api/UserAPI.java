package com.example.agricolab.api;

import com.example.agricolab.model.User;
import com.example.agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser() {
        return null;
    }

    @PostMapping
    public String postUser(@RequestBody User u) {
        return userService.addUser(u);
    }

    @PutMapping
    public void putUser() {
    }

    @DeleteMapping
    public void deleteUser() {
    }
}
