package agricolab.service;

import agricolab.dao.UserDAO;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("Firestore") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public int addUser(User user){
        return userDAO.createUser(user);
    }

    public User getUser(String email){
        return userDAO.getUser(email);
    }

    public ArrayList<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void deleteUser (String email){userDAO.deleteUser(email);}
}