package agricolab.api;


import agricolab.model.Mailing;
import agricolab.model.User;
import agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
@RestController
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> postUser(@RequestBody User u) {
        if(userService.addUser(u)){
            return new ResponseEntity<>("Usuario añadido", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Usuario o contraseña incorrecta", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("del/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        System.out.println("Successful delete from user " + email);
    }

    // GET METHODS
    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @GetMapping("/address/{email}")
    public Mailing getMailingByUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        return userService.getMailingByUser(email);

    }

    @PostMapping("/address/{email}")
    public boolean createMailing(@PathVariable String email, @RequestBody Mailing mailing) {
        return userService.createMailing(email, mailing);
    }
    @PutMapping("/address/{email}/{phoneNumber}")
    public boolean updateUserData(@PathVariable String email, @RequestBody Mailing mailing, @PathVariable long phoneNumber) {
        return userService.updateUserData(email, mailing,phoneNumber);
    }

    @GetMapping
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("dashboard/{email}")
    ArrayList<Integer> getDashboard(@PathVariable String email){
        return userService.getDashboard(email);
    }

}
