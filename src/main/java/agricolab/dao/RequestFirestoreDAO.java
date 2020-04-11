package agricolab.dao;

import agricolab.model.Request;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository()
public class RequestFirestoreDAO implements RequestDAO {

    @Override
    public int createRequest(Request request) {
        Firestore db=FirestoreClient.getFirestore();
        db.collection("request").add(request);
        System.out.println(request);
        return 0;
    }

    @Override
    public Request getRequest(Long id) {
        return null;
    }

    @Override
    public int updateRequest(Request r1, Request r2) {
        return 0;
    }

    @Override
    public int deleteRequest(Request r) {
        return 0;
    }

    @Override
    public ArrayList<Request> getAllRequest() {
        return null;
    }
}
