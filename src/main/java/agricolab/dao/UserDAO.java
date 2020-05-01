package agricolab.dao;

import agricolab.model.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;

public interface UserDAO {


    int createUser(User u);

    User getUser(String email);

    int updateUser(User u1, User u2);

    void deleteUser(String email);

    ArrayList<User> getAllUsers();

}