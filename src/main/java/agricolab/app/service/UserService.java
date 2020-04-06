package agricolab.app.service;

import agricolab.app.dao.UserDAO;
import agricolab.app.model.User;
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
}
