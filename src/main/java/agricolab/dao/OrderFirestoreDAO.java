package agricolab.dao;

import agricolab.model.Order;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository()
public class OrderFirestoreDAO implements OrderDAO {

    @Override
    public int createOrder(Order request) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("order").add(request);
        System.out.println(request);
        return 0;
    }

    @Override
    public int updateOrder(Order r1, Order r2) {
        return 0;
    }

    @Override
    public int deleteOrder(Order r) {
        return 0;
    }

    // READ METHODS
    @Override
    public Order getOrder(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("request").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document = null;
        Order ret = null;
        try {
            document = future.get();
            if (document.exists()) {
                ret = document.toObject(Order.class);
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
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allRequest = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("request");
        ApiFuture<QuerySnapshot> docs = requestRef.get();
        List<QueryDocumentSnapshot> docList = null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                allRequest.add(a.toObject(Order.class));
            }
            System.out.println(allRequest);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allRequest;
    }

    @Override
    public ArrayList<Order> getUserOrders(String email) {
        ArrayList<Order> userOrder = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("order");
        ApiFuture<QuerySnapshot> docs = orderRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList = null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                userOrder.add(a.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userOrder;
    }

    @Override
    public ArrayList<Order> getOfferOrders(String orderID) {
        ArrayList<Order> offerOrders = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("order");
        ApiFuture<QuerySnapshot> docs = orderRef.whereEqualTo("userEmail", orderID).get();
        List<QueryDocumentSnapshot> docList = null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                offerOrders.add(a.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return offerOrders;
    }
}
