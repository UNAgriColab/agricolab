package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {



    ID setOrderId();

    int getLastOrderId();

    int createOrder(Order request);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getOrdersByBuyer(String email);

    ArrayList<Order> getOrdersBySeller(String email);

    boolean updateOrder(Order r);

    void deleteOrder(String id);

    ArrayList<Order> getOrdersByOffer(String orderID);

}
