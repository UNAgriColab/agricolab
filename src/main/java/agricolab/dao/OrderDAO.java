package agricolab.dao;

import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {

    int createOrder(Order request);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getUserOrders(String email);

    int updateOrder(Order r1, Order r2);

    int deleteOrder(Order r);

    ArrayList<Order> getOfferOrders(String orderID);


}
