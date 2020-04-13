package agricolab.dao;

import agricolab.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("Firestore")
public class UserFirestoreDAO implements UserDAO {

    @Override
    public int createUser(User user) {
        Firestore db=FirestoreClient.getFirestore();
        System.out.println(user.getEmail());
        db.collection("user").document(user.getEmail()).set(user);
        System.out.println(user);
        return 1;
    }

    @Override
    public User getUser(String id){
        Firestore db=FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("user").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document = null;
        User ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(User.class);
                System.out.println("Nombre: " + ret.getName());
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers= new ArrayList<User>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference userRef=db.collection("user");
        ApiFuture<QuerySnapshot> docs= userRef.get();
        List<QueryDocumentSnapshot> docList= null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                allUsers.add(a.toObject(User.class));
                System.out.println(allUsers.size());
            }
            System.out.println(allUsers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUsers;
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
