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

    ArrayList<Order> getOrderBySeller(String email);

    ArrayList<Order> getUserOrders(String email);

    int updateOrder(Order r1, Order r2);

    void deleteOrder(String id);

    ArrayList<Order> getOfferOrders(String orderID);


}
