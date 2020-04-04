package agricolab.app.dao;

import agricolab.app.model.User;

import java.util.UUID;

public interface UserDAO {

    int createUser(UUID id, User user);

    default int createUser(User u) {
        UUID id = UUID.randomUUID();
        return createUser(id, u);
    }

    int readUser(UUID id);

    int updateUser(User u1, User u2);

    int deleteUser(User u);

}
