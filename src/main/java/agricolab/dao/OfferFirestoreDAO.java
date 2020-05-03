package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository()
public class OfferFirestoreDAO implements OfferDAO {
    ////////////////////////////////////////////////////////////////////////////////////
    //Basic CRUD(CREATE READ UPDATE DELETE)
    @Override
    public int createOffer(Offer offer) {
        Firestore db=FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("offer");
        String name = offer.getProductName();
        for (Offer o : gerOffersByUser(offer.getUserEmail())){
            if (name == o.getProductName()){
                return 0;
            }
        }
        ID id = setOfferId();
        offer.setId(id.toString());
        ref.document(id.toString()).set(offer);
        System.out.println(offer);
        return 0;
    }
    // READ
    @Override
    public Offer getOffer(String  id) {
        Firestore db=FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("offer").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document;
        Offer ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(Offer.class);
                System.out.println("Nombre: " + Objects.requireNonNull(ret).getProductName());
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }
    //UPDATE
    //
    @Override
    public boolean updateOffer(int id, Offer r) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        updates.put("userEmail", r.getUserEmail());
        updates.put("productName", r.getProductName());
        updates.put("presentation", r.getPresentation());
        updates.put("pricePresentation", r.getPricePresentation());
        updates.put("minQuantity", r.getMinQuantity());
        updates.put("description", r.getDescription());
        ApiFuture<WriteResult> ud = db.collection("offer").document(String.valueOf(id)).update(updates);
        try {
            System.out.println(ud.get().getUpdateTime());
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
    //DELETE
    @Override
    public void deleteOffer(String id){
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference requestRef=db.collection("offer");
        ApiFuture<WriteResult> writeResult = requestRef.document(id).delete();
    }
    ////////////////////////////////////////////////////////////////////////////////////
    //AUXILIARY METHODS
    //RETRIEVES AND AUTO-GENERATES ID'S FOR OFFERS
    ///////////////////////////////////////////////////////////////////////////////////
    @Override
    public ID setOfferId(){
        ID ret = new ID();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("ids").document("idoffer");
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(ID.class);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(ret).setId(ret.getId()+1);
        ref.set(ret);
        return ret;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    //RETRIEVES ALL OFFERS ON OFFER COLLECTION
    @Override
    public ArrayList<Offer> getAllOffers() {
        ArrayList<Offer> allOffers= new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference offerRef=db.collection("offer");
        ApiFuture<QuerySnapshot> docs= offerRef.get();
        List<QueryDocumentSnapshot> docList;
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
    ////////////////////////////////////////////////////////////////////////////////////
    //NO TENGO NI PUTA IDEA
    @Override
    public ArrayList<Offer> gerOffersByUser(String email){
        ArrayList<Offer> userOffers= new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference requestRef=db.collection("offer");
        ApiFuture<QuerySnapshot> docs= requestRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                userOffers.add(a.toObject(Offer.class));
            }
            System.out.println(userOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userOffers;
    }

    @Override
    public int getLastOfferId(){
        int ret = 0;
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("ids").document("idoffer");
        ApiFuture<DocumentSnapshot> future = ref.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                ret = Objects.requireNonNull(document.toObject(ID.class)).getId();
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
