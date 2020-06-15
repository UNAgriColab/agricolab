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
        offer.setId(id.getId());
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
        Map<String, Object> updates = new HashMap<>();
        updates.put("presentation", r.getPresentation());
        updates.put("pricePresentation", r.getPricePresentation());
        updates.put("minQuantity", r.getMinQuantity());
        updates.put("description", r.getDescription());
        ApiFuture<WriteResult> ud = db.collection("offer").document(String.valueOf(r.getId())).update(updates);
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
    public boolean deleteOffer(String id) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("offer");
        Map<String, Object> updates = new HashMap<>();
        updates.put("state", false);
        ApiFuture<WriteResult> cancel = requestRef.document(id).update(updates);
        return true;
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
    public ArrayList<Offer> getOffersByUserAndProduct(String email, String productName) {
        ArrayList<Offer> userOffers = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("offer");
        ApiFuture<QuerySnapshot> docs = requestRef.whereEqualTo("sellerEmail", email)
                .whereEqualTo("state", true)
                .whereEqualTo("productName", productName).get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                userOffers.add(a.toObject(Offer.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userOffers;
    }


    @Override
    public ArrayList<Offer> getActiveOffers(String productName, double minPrice, double maxPrice,
                                            int presentation, int order, int page, int pivot)
            throws ExecutionException, InterruptedException {

        ArrayList<Offer> activeOffers = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("offer");
        DocumentSnapshot last = null;
        if (page != 1) {
            last = orderRef.document(String.valueOf(pivot)).get().get();
            Offer temp = last.toObject(Offer.class);
            System.out.println("last called = " + temp);
        }
        Query q = orderRef.whereEqualTo("state", true);
        if (order == 0) {
            System.out.println("ORDEN POR DEFECTO");
            if (page == 1 || page == 2) {
                System.out.println("NextOrFirstPage");
                q = q.orderBy("id", Query.Direction.ASCENDING);
            } else if (page == 0) {
                System.out.println("LastPage");
                q = q.orderBy("id", Query.Direction.DESCENDING);
            }
        }
        if (order == 1) {
            System.out.println("Orden por precio de presentacion ascendente");
            if (page == 1 || page == 2) {
                System.out.println("nextPage");
                q = q.orderBy("pricePresentation", Query.Direction.ASCENDING);
            } else if (page == 0) {
                System.out.println("LastPage");
                q = q.orderBy("pricePresentation", Query.Direction.DESCENDING);
            }
        }
        if (order == 2) {
            System.out.println("orden por precio de presentacion descendente");
            if (page == 1 || page == 2) {
                System.out.println("nextPage");
                q = q.orderBy("pricePresentation", Query.Direction.DESCENDING);
            } else if (page == 0) {
                System.out.println("LastPage");
                q = q.orderBy("pricePresentation", Query.Direction.ASCENDING);
            }
        }
        if (order == 3) {
            System.out.println("Orden por calificación");
            if (page == 1 || page == 2) {
                q = q.orderBy("qualification", Query.Direction.DESCENDING);
            } else if (page == 0) {
                q = q.orderBy("qualification", Query.Direction.ASCENDING);
            }
        }
        if (!productName.equals("all")) {
            q = q.whereEqualTo("productName", productName);
        }
        if (presentation != 0) {
            q = q.whereEqualTo("presentation", presentation);
        }
        if (minPrice != 0 && order != 3 && order!=0) {
            q = q.whereGreaterThanOrEqualTo("pricePresentation", minPrice);
        }
        if (maxPrice != 0 && order != 3 && order!=0) {
            q = q.whereLessThanOrEqualTo("pricePresentation", maxPrice);
        }
        if (page != 1) {
            q = q.startAfter(last);
        }
        q = q.limit(10);
        ApiFuture<QuerySnapshot> future = q.get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = future.get(30, TimeUnit.SECONDS).getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                activeOffers.add(a.toObject(Offer.class));
                System.out.println(a.toObject(Offer.class));
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

    @Override
    public boolean updateOfferReviews(String id, double qualification, int numberOfReviews) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        updates.put("qualification", qualification);
        updates.put("numberOfReviews", numberOfReviews);
        ApiFuture<WriteResult> ud = db.collection("offer").document(id).update(updates);
        return true;
    }
}
