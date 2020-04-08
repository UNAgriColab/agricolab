package agricolab.dao;

import agricolab.model.Offers;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public class OfferFirestoreDAO implements OffersDAO{

    @Override
    public int createOffer(UUID id, Offers offers) {
        ApiFuture<WriteResult> promise = FirestoreClient.getFirestore().collection("offer").document(id.toString()).set(offers);

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
    public int readOffer(UUID id) {
        return 0;
    }

    @Override
    public int updateOffer(Offers o1, Offers o2) {
        return 0;
    }

    @Override
    public int deleteOffer(Offers o) {
        return 0;
    }
}
