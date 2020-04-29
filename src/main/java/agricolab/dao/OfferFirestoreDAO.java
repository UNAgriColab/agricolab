package agricolab.dao;

import agricolab.model.Offer;
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
    public int createOffer(Offer offer) {
        Firestore db=FirestoreClient.getFirestore();
        db.collection("offer").add(offer);
        System.out.println(offer);
        return 0;
    }

    // READ METHODS
    @Override
    public Offer getOffer(String  id) {
        Firestore db=FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("offer").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document = null;
        Offer ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(Offer.class);
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
    public ArrayList<Offer> getAllOffers() {
        ArrayList<Offer> allOffers= new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference offerRef=db.collection("offer");
        ApiFuture<QuerySnapshot> docs= offerRef.get();
        List<QueryDocumentSnapshot> docList= null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                allOffers.add(a.toObject(Offer.class));
            }
            System.out.println(allOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allOffers;
    }


    @Override
    public ArrayList<Offer> getUserOffers(String email){
        ArrayList<Offer> userOffers= new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference requestRef=db.collection("offer");
        ApiFuture<QuerySnapshot> docs= requestRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList= null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                userOffers.add(a.toObject(Offer.class));
                System.out.println(userOffers.size());
            }
            System.out.println(userOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userOffers;
    }

    @Override
    public void deleteOffer(String id){
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference requestRef=db.collection("offer");
        ApiFuture<WriteResult> writeResult = requestRef.document(id).delete();
    }
}
