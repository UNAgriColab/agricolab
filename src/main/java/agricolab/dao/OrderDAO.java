package agricolab.dao;

import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {

    int createOrder(Order request);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getUserOrders(String email);

    boolean updateOrder(Order r);

    void deleteOrder(String id);

    ArrayList<Order> getOfferOrders(String orderID);


}
