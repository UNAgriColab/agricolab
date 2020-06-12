package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Product {
    private String id;
    private String categoria;
    private ArrayList<String> productNames;

    public Product() {

    }

    public Product(@JsonProperty String id,
                   @JsonProperty String categoria,
                   @JsonProperty ArrayList<String> productNames) {
        this.id = id;
        this.categoria = categoria;
        this.productNames = productNames;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", productos=" + productNames +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(ArrayList<String> productos) {
        this.productNames = productos;
    }
}
