package agricolab.app.service;

import agricolab.app.dao.UserDAO;
import agricolab.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("Firestore") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public int addUser(User user){
        return userDAO.createUser(user);
    }
    public User getUser(String id){
        return userDAO.getUser(id);
    }
}
