package agricolab.dao;

import agricolab.model.Request;
import agricolab.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository()
public class RequestFirestoreDAO implements RequestDAO {

    @Override
    public int createRequest(Request request) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("request").add(request);
        System.out.println(request);
        return 0;
    }

    @Override
    public int updateRequest(Request r1, Request r2) {
        return 0;
    }

    @Override
    public int deleteRequest(Request r) {
        return 0;
    }

    // READ METHODS
    @Override
    public Request getRequest(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("request").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document = null;
        Request ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(Request.class);
                System.out.println("Nombre: " + ret.getProductName());
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ArrayList<Request> getAllRequests() {
        ArrayList<Request> allRequest = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("request");
        ApiFuture<QuerySnapshot> docs = requestRef.get();
        List<QueryDocumentSnapshot> docList = null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                allRequest.add(a.toObject(Request.class));
                System.out.println(allRequest.size());
            }
            System.out.println(allRequest);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allRequest;
    }

    @Override
    public ArrayList<Request> getUserRequests(String email) {
        ArrayList<Request> userRequests = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("request");
        ApiFuture<QuerySnapshot> docs = requestRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList = null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                userRequests.add(a.toObject(Request.class));
                System.out.println(userRequests.size());
            }
            System.out.println(userRequests);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userRequests;
    }
}
