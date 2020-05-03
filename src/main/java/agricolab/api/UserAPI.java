package agricolab.api;


import agricolab.model.Login;
import agricolab.model.User;
import agricolab.security.JWTUtil;
import agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;
    private JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserAPI(UserService userService, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
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

    @PostMapping("/auth")
    public String generateToken(@RequestBody Login user) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
            );
        }catch (Exception e){
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

}
