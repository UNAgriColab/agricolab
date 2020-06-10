package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Repository
public class OfferFirestoreDAO implements OfferDAO {
    ////////////////////////////////////////////////////////////////////////////////////
    //Basic CRUD(CREATE READ UPDATE DELETE)
    @Override
    public boolean createOffer(Offer offer) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("offer");
        ID id = setOfferId();
        offer.setId(id.toString());
        ref.document(id.toString()).set(offer);
        System.out.println(offer);
        return true;
    }

    // READ
    @Override
    public Offer getOffer(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("offer").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document;
        Offer ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(Offer.class);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }

    //UPDATE
    @Override
    public boolean updateOffer(Offer r) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("offer").document(r.getId());
        ApiFuture<WriteResult> future = ref.set(r);
        return false;
    }

    //DELETE
    @Override
    public void deleteOffer(String id) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("offer");
        ApiFuture<WriteResult> writeResult = requestRef.document(id).delete();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //AUXILIARY METHODS
    //RETRIEVES AND AUTO-GENERATES ID'S FOR OFFERS
    ///////////////////////////////////////////////////////////////////////////////////
    @Override
    public ID setOfferId() {
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
        Objects.requireNonNull(ret).setId(ret.getId() + 1);
        ref.set(ret);
        return ret;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //RETRIEVES ALL OFFERS ON OFFER COLLECTION
    @Override
    public ArrayList<Offer> getAllOffers() {
        ArrayList<Offer> allOffers = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference offerRef = db.collection("offer");
        ApiFuture<QuerySnapshot> docs = offerRef.get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                allOffers.add(a.toObject(Offer.class));
            }
            System.out.println(allOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allOffers;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //DEVUELVE OFFERS DE UN USUARIO DADO SU EMAIL
    public ArrayList<Offer> getOffersByUser(String email) {
        ArrayList<Offer> userOffers = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("offer");
        ApiFuture<QuerySnapshot> docs = requestRef.whereEqualTo("sellerEmail", email).get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                userOffers.add(a.toObject(Offer.class));
            }
            System.out.println(userOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userOffers;
    }

    @Override
    public ArrayList<Offer> getOffersByUserAndProduct(String email , String productName){
        ArrayList<Offer> userOffers= new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference requestRef=db.collection("offer");
        ApiFuture<QuerySnapshot> docs= requestRef.whereEqualTo("sellerEmail", email)
                .whereEqualTo("state"  ,true)
                .whereEqualTo("productName" , productName ).get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                userOffers.add(a.toObject(Offer.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userOffers;
    }


    @Override
    public ArrayList<Offer> getActiveOffers(String productName, double minPrice,  double maxPrice , int presentation,int order) {
        int elementsPerPage = 1;

        ArrayList<Offer> activeOffers = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("offer");
        Query q = orderRef.whereEqualTo("state", true);
        if(order == 1){
            q = q.orderBy("pricePresentation" , Query.Direction.ASCENDING);
        }
        if(order == 2){
            q = q.orderBy("pricePresentation" , Query.Direction.DESCENDING);
        }
        if (order == 3){
            q = q.orderBy("qualification" , Query.Direction.DESCENDING);
        }
        if(!productName.equals("all")){
            q = q.whereEqualTo("productName" , productName);
        }
        if(presentation != 0){
            q = q.whereEqualTo("presentation" , presentation);
        }
        if(minPrice != 0 && order !=3){
            q = q.whereGreaterThanOrEqualTo("pricePresentation", minPrice);
        }
        if(maxPrice != 0 && order !=3){
            q = q.whereLessThan("pricePresentation" , maxPrice);
        }

        ApiFuture<QuerySnapshot> future = q.get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = future.get(30, TimeUnit.SECONDS).getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                activeOffers.add(a.toObject(Offer.class));
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return activeOffers;
    }


    //AUXILIARY METHODS

    public int getLastOfferId() {
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
