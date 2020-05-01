package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Order;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface OrderDAO {

    ID getID();

    int createOrder(Order request);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getUserOrders(String email);

    boolean updateOrder(Order r);

    void deleteOrder(String id);

    ArrayList<Order> getOfferOrders(String orderID);


}
