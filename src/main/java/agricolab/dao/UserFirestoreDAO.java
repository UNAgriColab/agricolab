package agricolab.dao;

import agricolab.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.*;
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
    public User readUser(String id) {
        Firestore db=FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("user").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

//        User city = null;
//        if (document.exists()) {
//            // convert document to POJO
//            city = document.toObject(User.class);
//            System.out.println(city);
//        } else {
//            System.out.println("No such document!");
//        }
//        return city;
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
