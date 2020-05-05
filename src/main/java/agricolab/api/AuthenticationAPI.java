package agricolab.api;

import agricolab.model.JwtRequest;
import agricolab.model.JwtResponse;
import agricolab.security.JwtUtil;
import agricolab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationAPI {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UserService userService;

    @Autowired
    public AuthenticationAPI(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService= userService;
    }

    @PostMapping("/api/auth")
    public String generateToken(@RequestBody JwtRequest user) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
            );
        }catch (Exception e){
            throw new Exception("invalid username/password");
        }

        String tt= jwtUtil.generateToken(userService.loadUserByUsername(user.getEmail()));
        System.out.println(tt);
        return tt;
    }

}
