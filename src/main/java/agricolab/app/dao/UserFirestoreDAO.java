package agricolab.app.dao;

import agricolab.app.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository("Firestore")
public class UserFirestoreDAO implements UserDAO {

    @Override
    public int createUser(User user) {
        Firestore db=FirestoreClient.getFirestore();
        System.out.println(user.getId());
        db.collection("users").document(user.getEmail()).set(user);
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
        CollectionReference userRef=db.collection("users");
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
