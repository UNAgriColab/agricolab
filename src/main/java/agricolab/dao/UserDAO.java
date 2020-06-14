package agricolab.dao;

import agricolab.model.Mailing;
import agricolab.model.User;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface UserDAO {

    boolean createUser(User u);

    User getUser(String email);

    void deleteUser(String email);

    ArrayList<User> getAllUsers();

    Mailing getMailingByUser(String email) throws ExecutionException, InterruptedException;

    boolean createMailing(User user);//, Mailing mailing);

    boolean updateUserData(String email, Mailing mailing, long phoneNumber);

    boolean updateUserQualification(String email, double qualification);
}