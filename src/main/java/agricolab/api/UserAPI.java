package agricolab.api;


import agricolab.model.User;
import agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void postUser(@RequestBody User u) {
        userService.addUser(u);
        System.out.println("Successful");
    }

    @DeleteMapping("del/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        System.out.println("Successful delete from user " + email);
    }

    // GET METHODS
    @GetMapping ("/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @GetMapping
    public ArrayList<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
