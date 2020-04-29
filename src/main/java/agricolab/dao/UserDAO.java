package agricolab.dao;

import agricolab.model.User;
import java.util.ArrayList;

public interface UserDAO {

    int createUser(User u);

    User getUser(String email);

    int updateUser(User u1, User u2);

    void deleteUser(String email);

    ArrayList<User> getAllUsers();

}