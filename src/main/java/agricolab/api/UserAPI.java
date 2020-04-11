package agricolab.api;


import agricolab.model.User;
import agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @PostMapping
    public void postUser(@RequestBody User u) {
        userService.addUser(u);
        System.out.println("Successful");
    }

    @GetMapping
    public ArrayList<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping
    public void putUser() {
    }

    @DeleteMapping
    public void deleteUser() {
    }
}
