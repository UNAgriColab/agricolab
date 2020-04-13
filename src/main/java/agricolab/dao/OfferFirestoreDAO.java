package agricolab.dao;

import agricolab.model.Offers;
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
public class OfferFirestoreDAO implements OfferDAO {

    @Override
    public int createOffer(Offers offer) {
        Firestore db=FirestoreClient.getFirestore();
        db.collection("offer").add(offer);
        System.out.println(offer);
        return 0;
    }

    @Override
    public Offers getOffer(String  id) {
        Firestore db=FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("offer").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document = null;
        Offers ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(Offers.class);
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
    public ArrayList<Offers> getAllOffers() {
        ArrayList<Offers> allOffers= new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference offerRef=db.collection("offer");
        ApiFuture<QuerySnapshot> docs= offerRef.get();
        List<QueryDocumentSnapshot> docList= null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                allOffers.add(a.toObject(Offers.class));
                System.out.println(allOffers.size());
            }
            System.out.println(allOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allOffers;
    }
}
