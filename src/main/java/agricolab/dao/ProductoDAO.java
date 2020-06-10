package agricolab.dao;

import agricolab.model.Producto;

import java.util.ArrayList;

public interface ProductoDAO {

    ArrayList<Producto> getProductos();

    boolean addProduct(Producto producto);

}
