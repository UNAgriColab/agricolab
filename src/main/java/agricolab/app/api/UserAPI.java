package agricolab.app.api;

import agricolab.app.model.User;
import agricolab.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
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
