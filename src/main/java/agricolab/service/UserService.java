package agricolab.service;

import agricolab.dao.UserDAO;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    @Qualifier("Firestore")
    private static UserDAO userDAO;

    public UserService(UserDAO userdao) {
        this.userDAO = userdao;
    }

    public String addUser(User user) {
        int error = userDAO.createUser(user);
        if (error == 0) {
            return "Sucessfully added User.";
        } else {
            return "Unable to add User. Error: " + error;
        }
    }
    public User getUser(String id){
        return userDAO.readUser(id);
    }
}
