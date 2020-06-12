package agricolab.dao;

import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {


    boolean createOrder(Order request);

    int getLastOrderId();

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getOrdersByBuyer(String email);

    ArrayList<Order> getActiveOrdersByBuyer(String email);

    ArrayList<Order> getOrdersBySeller(String email);

    ArrayList<Order> getActiveOrdersBySeller(String email);

    void deleteOrder(String id);

    ArrayList<Order> getActiveOrdersByBuyerAndOffer(String email, String offer);

    ArrayList<Order> getActiveOrders();

    ArrayList<Order> getOrdersByProduct(String productName);

    boolean updateOrderStatus(String id, int i);
}
