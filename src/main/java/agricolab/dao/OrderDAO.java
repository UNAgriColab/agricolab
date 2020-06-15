package agricolab.dao;

import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {


    boolean createOrder(Order request);


    //UNUSED METHODS ------------------------------------------------
    int getLastOrderId();

    boolean updateOrder(int id, double qualification);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    //Buyer methods
    ArrayList<Order> getOrdersByBuyer(String email, String productName, int state);

    ArrayList<Order> getActiveOrdersByBuyer(String email, String productName, int state);

    //Seller methods
    ArrayList<Order> getOrdersBySeller(String email, String productName, int state);

    ArrayList<Order> getActiveOrdersBySeller(String email, String productName, int state);

    //Update methods

    boolean deleteOrder(String id);


    boolean updateOrderStatus(String id, int i);
}
