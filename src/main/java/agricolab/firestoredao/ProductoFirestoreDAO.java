package agricolab.firestoredao;

import agricolab.dao.ProductoDAO;
import agricolab.model.Product;
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

    public ArrayList<Product> getProductos() {
        ArrayList<Product> products = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference orderRef = db.collection(FirestoreDAO.COLLECTION_PRODUCT);
        ApiFuture<QuerySnapshot> docs = orderRef.get();
        List<QueryDocumentSnapshot> docList;
        try {
            docList = docs.get().getDocuments();
            for (QueryDocumentSnapshot a : docList) {
                products.add(a.toObject(Product.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference ref = db.collection(FirestoreDAO.COLLECTION_PRODUCT);
        String id = product.getId();
        ref.document(id).set(product);
        return true;
    }

}
