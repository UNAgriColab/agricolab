package agricolab.dao;

import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.model.ID;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository()
public class OrderFirestoreDAO implements OrderDAO {
    //Basic CRUD(CREATE READ UPDATE DELETE)
    @Override
    public int createOrder(Order order) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("order");
        ID id = setOrderId();
        order.setId(id.toString());
        ref.document(id.toString()).set(order);
        System.out.println(order);
        return 0;
    }
    // READ
    @Override
    public Order getOrder(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("order").document(id);
        ApiFuture<DocumentSnapshot> future = ref.get();
        Order ret = null;
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                ret = document.toObject(Order.class);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ret;
    }
    // UPDATE
    @Override
    public boolean updateOrder(Order r) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        updates.put("offerReference", r.getOfferReference());
        updates.put("userEmail", r.getUserEmail());
        updates.put("unit", r.getUnit());
        updates.put("totalPrice", r.getTotalPrice());
        updates.put("description", r.getDescription());
        ApiFuture<WriteResult> ud = db.collection("order").document(String.valueOf(r.getId())).update(updates);
        try {
            System.out.println(ud.get().getUpdateTime());
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
    // DELETE
    @Override
    public void deleteOrder(String id) {
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference requestRef=db.collection("order");
        ApiFuture<WriteResult> writeResult = requestRef.document(id).delete();
    }
    //AUXILIARY METHODS
    @Override
    public  ID setOrderId(){
        ID ret = new ID();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("ids").document("idorder");
        ApiFuture<DocumentSnapshot> future = ref.get();
        DocumentSnapshot document = null;
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
        ret.setId(ret.getId()+1);
        ref.set(ret);
        return ret;
    }
    //RETRIEVES ALL ORDERS FROM ORDER COLLECTION
    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allRequest = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("order");
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
    //
    @Override
    public ArrayList<Order> getOrdersByBuyer(String email) {
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByOffer(String orderID) {
        ArrayList<Order> offerOrders = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("order");
        ApiFuture<QuerySnapshot> docs = orderRef.whereEqualTo("offerReference", orderID).get();
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

    @Override
    public ArrayList<Order> getOrdersBySeller(String email){
        ArrayList<String> userOffers= new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        Firestore db= FirestoreClient.getFirestore();
        CollectionReference offerRef=db.collection("offer");
        //buscar todas los ofertas del vendedor
        ApiFuture<QuerySnapshot> docs= offerRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList= null;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a: docList){
                userOffers.add(a.toObject(Offer.class).getId());
            }
            System.out.println(userOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //para todas las ofertas buscar las ordenes con ese id
        for(String offer: userOffers){
            for (Order o:getOrdersByOffer(offer)){
                orders.add(o);
            }
        }
        return orders;
    };
}
