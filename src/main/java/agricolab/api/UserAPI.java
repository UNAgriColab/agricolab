package agricolab.api;


import agricolab.model.User;
import agricolab.security.JwtUtil;
import agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;
    private JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserAPI(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil =jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public boolean postUser(@RequestBody User u) {
        return userService.addUser(u);
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
