package agricolab.app.dao;

import agricolab.app.model.User;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("Firestore")
public class UserFirestoreDAO implements UserDAO {

    @Override
    public int createUser(UUID id, User user) {
        Firestore firesDB = FirestoreClient.getFirestore();
        firesDB.collection("user").add(user);
        return 1;
    }

    @Override
    public int readUser(UUID id) {
        return 0;
    }

    @Override
    public int updateUser(User u1, User u2) {
        return 0;
    }

    @Override
    public int deleteUser(User u) {
        return 0;
    }
}
