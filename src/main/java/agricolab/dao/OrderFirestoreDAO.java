package agricolab.dao;

import agricolab.JsonModel.Update;
import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.model.ID;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
public class OrderFirestoreDAO implements OrderDAO {



    private final OfferDAO offerDAO;

    @Autowired
    public OrderFirestoreDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }
    //Basic CRUD(CREATE READ UPDATE DELETE)
    @Override
    public boolean createOrder(Order order) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("order");
        try {
            Offer offer= db.collection("offer").document(order.getOfferReference()).get().get().toObject(Offer.class);
            ID id = setOrderId();
            order.setId(id.toString());
            order.setTotalPrice(Objects.requireNonNull(offer).getPricePresentation()*order.getNumberOfUnits());
            ref.document(id.toString()).set(order);
            System.out.println(order);
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getLastOrderId() {
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
    public boolean updateOrderByBuyer(String orderId) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        updates.put("state", 0);
        ApiFuture<WriteResult> ud = db.collection("order").document(orderId).update(updates);
        try {
            System.out.println(ud.get().getUpdateTime());
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrderBySeller(Update changes) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> updates = new HashMap<>();
        ApiFuture<WriteResult> ud;
        ApiFuture<DocumentSnapshot> actual = db.collection("order").document(changes.getOrderId()).get();
        try {
            Order temp = actual.get().toObject(Order.class);
            if (!changes.isCanceled()){
                updates.put("state", 0);
                db.collection("order").document(changes.getOrderId()).update(updates);
                return true;
            }
                if(temp == null){
                    return false;
                }
                int stateTemp=temp.getState();
                if(stateTemp!=0){
                    stateTemp++;
                    updates.put("state", stateTemp);
                    ud= db.collection("order").document(changes.getOrderId()).update(updates);
                    System.out.println(ud.get().getUpdateTime());
                    return true;
                }
                else{
                    return false;
                }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    @Override
    public void deleteOrder(String id) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("order");
        ApiFuture<WriteResult> writeResult = requestRef.document(id).delete();
        // writeResult.get();

    }

    //AUXILIARY METHODS
    @Override
    public ID setOrderId() {
        ID ret = new ID();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ref = db.collection("ids").document("idorder");
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

    //RETRIEVES ALL ORDERS FROM ORDER COLLECTION
    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> allRequest = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference requestRef = db.collection("order");
        ApiFuture<QuerySnapshot> docs = requestRef.get();
        List<QueryDocumentSnapshot> docList;
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
        ArrayList<Order> userOrder = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("order");
        ApiFuture<QuerySnapshot> docs = orderRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList;
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
    public ArrayList<Order> getOrdersByOffer(String orderID) {
        ArrayList<Order> offerOrders = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("order");
        ApiFuture<QuerySnapshot> docs = orderRef.whereEqualTo("offerReference", orderID).whereGreaterThan("state" ,0 ).get();
        List<QueryDocumentSnapshot> docList;
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
    public ArrayList<Order> getActiveOrders() {
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByProduct(String productName) {
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersBySeller(String email) {
        ArrayList<String> userOffers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference offerRef = db.collection("offer");
        //buscar todas los ofertas del vendedor
        ApiFuture<QuerySnapshot> docs = offerRef.whereEqualTo("userEmail", email).get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                userOffers.add(a.toObject(Offer.class).getId());
            }
            System.out.println(userOffers);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //para todas las ofertas buscar las ordenes con ese id
        for(String offer: userOffers){
            orders.addAll(getOrdersByOffer(offer));
        }
        return orders;
    }

}
