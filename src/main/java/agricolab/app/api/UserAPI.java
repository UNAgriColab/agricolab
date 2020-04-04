package agricolab.app.api;


import agricolab.app.model.User;
import agricolab.app.service.UserService;
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
    public void postUser(@RequestBody User u) {
        userService.addUser(u);
    }

    @PutMapping
    public void putUser() {
    }

    @DeleteMapping
    public void deleteUser() {
    }
}
