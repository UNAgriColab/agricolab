package agricolab.app.dao;

import agricolab.app.model.User;

import java.util.UUID;

public interface UserDAO {

     int createUser(User u);

    User getUser(String id);

    int updateUser(User u1, User u2);

    int deleteUser(User u);

}
