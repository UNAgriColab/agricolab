package agricolab.app.dao;

import agricolab.app.model.User;
import antlr.collections.List;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface UserDAO {

     int createUser(User u);

    User getUser(String id);

    int updateUser(User u1, User u2);

    int deleteUser(User u);

    ArrayList<User> getAllUsers();
}
