package agricolab.dao;

import agricolab.JsonModel.Update;
import agricolab.model.ID;
import agricolab.model.Order;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface OrderDAO {

    ID setOrderId();

    int createOrder(Order request);

    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    ArrayList<Order> getOrdersByBuyer(String email);

    ArrayList<Order> getOrdersBySeller(String email);

    boolean updateOrderByBuyer(String orderId);

    void deleteOrder(String id);

    ArrayList<Order> getOrdersByOffer(String orderID);

    boolean updateOrderBySeller(Update changes);
}
