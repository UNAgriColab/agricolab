package agricolab.dao;

import agricolab.JsonModel.Update;
import agricolab.model.ID;
import agricolab.model.Order;
import agricolab.model.User;

import java.util.ArrayList;

public interface OrderDAO {


    boolean createOrder(Order request);


    Order getOrder(String id);

    ArrayList<Order> getAllOrders();

    //Buyer methods
    ArrayList<Order> getOrdersByBuyer(String email);

    ArrayList<Order> getActiveOrdersByBuyer(String email);

    //Seller methods
    ArrayList<Order> getOrdersBySeller(String email);

    ArrayList<Order> getActiveOrdersBySeller(String email , String productName , int state);

    ArrayList<Order> getActiveOrdersByBuyerAndOffer(String email, String offerRef);

    //Update methods

    boolean updateOrderByBuyer(String orderId);

    boolean updateOrderBySeller(Update changes);

    boolean updateOrder(Order order);

    //delete method
    void deleteOrder(String id);

}
