package agricolab.dao;

import agricolab.JsonModel.Update;
import agricolab.model.ID;
import agricolab.model.Order;

import java.util.ArrayList;

public interface OrderDAO {



    ID setOrderId();

    int getLastOrderId();

    String createOrder(Order request);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getOrdersByBuyer(String email);

    ArrayList<Order> getOrdersBySeller(String email);

    ArrayList<Order> getActiveOrders ();
    boolean updateOrderByBuyer(String r);

    boolean updateOrderBySeller(Update changes);

    void deleteOrder(String id);

    ArrayList<Order> getOrdersByOffer(String orderID);

}
