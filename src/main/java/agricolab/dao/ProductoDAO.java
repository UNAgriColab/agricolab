package agricolab.dao;

import agricolab.model.Product;

import java.util.ArrayList;

public interface ProductoDAO {

    ArrayList<Product> getProductos();

    boolean addProduct(Product product);

}
