package agricolab.dao;

import agricolab.model.Producto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ProductoFirestoreDAO implements ProductoDAO {

    @Override
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos= new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection("producto");
        ApiFuture<QuerySnapshot> docs = orderRef.get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                productos.add(a.toObject(Producto.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return  productos;
    }

    @Override
    public boolean addProduct(Producto producto) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection("producto");
        String id =  producto.getId();
        ref.document(id).set(producto);
        return true;
    }

}
