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
        System.out.println(offer.getId());
        db.collection("offer").document(Long.toString(offer.getId())).set(offer);
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
