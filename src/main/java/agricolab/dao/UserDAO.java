package agricolab.dao;

import agricolab.model.User;

import java.util.UUID;

public interface UserDAO {

    int createUser(UUID id, User u);

    default int createUser(User u) {
        UUID id = UUID.randomUUID();
        return createUser(id, u);
    }

    User readUser(String id);

    int updateUser(User u1, User u2);

    int deleteUser(User u);

}
