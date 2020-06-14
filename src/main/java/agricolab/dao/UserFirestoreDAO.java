package agricolab.dao;

import agricolab.model.Mailing;
import agricolab.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository("Firestore")
public class UserFirestoreDAO implements UserDAO {

    @Override
    public boolean createUser(User user) {

        Firestore db = FirestoreClient.getFirestore();
        db.collection("user").document(user.getEmail()).set(user);
        System.out.println(user);
        return true;

    }

    @Override
    public User getUser(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("user").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document;
        User ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(User.class);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void deleteUser(String email) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("user");
        ApiFuture<WriteResult> writeResult = requestRef.document(email).delete();
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference userRef = db.collection("user");
        ApiFuture<QuerySnapshot> docs = userRef.get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                allUsers.add(a.toObject(User.class));
            }
            System.out.println(allUsers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public Mailing getMailingByUser(String email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference request = db.collection("user");
        ApiFuture<DocumentSnapshot> obj = request.document(email).get();
        return Objects.requireNonNull(Objects.requireNonNull(obj.get().toObject(User.class)).getMailing());
    }

    @Override
    public boolean createMailing(User user) {//, Mailing mailing) {
        Firestore db = FirestoreClient.getFirestore();
        //Map<String, Object> updates = new HashMap<>();
        //updates.put("mailing", mailing);
        db.collection("user").document(user.getEmail()).set(user);
        return true;
    }

    @Override
        public boolean updateUserData(String email, Mailing mailing, long phoneNumber) {
            Firestore db = FirestoreClient.getFirestore();
        CollectionReference user = db.collection("user");
        User u = getUser(email);
        u.setMailing(mailing);
        u.setPhoneNumber(phoneNumber);
        ApiFuture<WriteResult> update = user.document(email).set(u);
        return true;
    }

    @Override
    public boolean updateUserQualification(String email, double qualification) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference user = db.collection("user");
        User u = getUser(email);
        try {
            user.document(email).update("qualification",qualification,"numberOfReviews",u.getNumberOfReviews()+1).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
