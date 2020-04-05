package agricolab.app.dao;

import agricolab.app.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository("Firestore")
public class UserFirestoreDAO implements UserDAO {

    @Override
    public int createUser(UUID id, User user) {
        ApiFuture<WriteResult> promise = FirestoreClient.getFirestore().collection("user").document(id.toString()).set(user);

        try {
            System.out.println(promise.get().getUpdateTime());
            return 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 1;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 2;
        }
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
