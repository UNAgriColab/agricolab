package agricolab.dao;

import agricolab.model.Mailing;
import agricolab.model.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface UserDAO {

    boolean createUser(User u);

    User getUser(String email);

    int updateUser(User u1, User u2);

    void deleteUser(String email);

    ArrayList<User> getAllUsers();

    Mailing getMailingByUser(String email) throws ExecutionException, InterruptedException;
}