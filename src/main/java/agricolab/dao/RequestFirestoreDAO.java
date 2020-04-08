package agricolab.dao;

import agricolab.model.Request;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public class RequestFirestoreDAO implements RequestDAO {

    @Override
    public int createRequest(UUID id, Request request) {
        ApiFuture<WriteResult> promise = FirestoreClient.getFirestore().collection("request").document(id.toString()).set(request);

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
    public int readRequest(UUID id) {
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
}
