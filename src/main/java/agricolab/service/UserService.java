package agricolab.service;

import agricolab.dao.UserDAO;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserService implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("Firestore") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean addUser(User user) {
        return userDAO.createUser(user);
    }

    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(String email) {
        userDAO.deleteUser(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUser(s);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

}