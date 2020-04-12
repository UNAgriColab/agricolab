package agricolab.dao;

import agricolab.model.Offers;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository()
public class OfferFirestoreDAO implements OffersDAO{

    @Override
    public int createOffer(Offers offer) {
        Firestore db=FirestoreClient.getFirestore();
        db.collection("offer").add(offer);
        System.out.println(offer);
        return 0;
    }

    @Override
    public Offers getOffer(Long id) {
        return null;
    }

    @Override
    public ArrayList<Offers> getAllOffers() {
        return null;
    }
}
