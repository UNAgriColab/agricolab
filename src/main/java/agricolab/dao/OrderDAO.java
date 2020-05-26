package agricolab.dao;

import agricolab.JsonModel.Update;
import agricolab.model.ID;
import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {


    ID setOrderId();

    boolean createOrder(Order request);

    int getLastOrderId();

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getOrdersByBuyer(String email);

    ArrayList<Order> getActiveOrdersByBuyer(String email);

    ArrayList<Order> getOrdersBySeller(String email);
    
    ArrayList<Order> getActiveOrdersBySeller(String email);

    boolean updateOrderByBuyer(String orderId);

    void deleteOrder(String id);

    ArrayList<Order> getActiveOrdersByBuyerAndOffer(String email, String offer);

    ArrayList<Order> getOrdersByOffer(String orderID);

    ArrayList<Order> getActiveOrders();

    ArrayList<Order> getOrdersByProduct(String productName);

    boolean updateOrderBySeller(Update changes);
}
