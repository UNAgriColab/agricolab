package agricolab.service;

import agricolab.dao.OrderDAO;
import agricolab.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OrderService {


    private  OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    public int addOrder(Order order){
        return orderDAO.createOrder(order);
    }

    public Order getOrder(String id){ return orderDAO.getOrder(id); }

    public ArrayList<Order> getAllOrders(){
        return orderDAO.getAllOrders();
    }

    public ArrayList<Order> getOrdersByBuyer(String email){
        return orderDAO.getOrdersByBuyer(email);
    }

    public ArrayList<Order> getOrdersByOffer(String offerId){
        return orderDAO.getOrdersByOffer(offerId);
    }

    public ArrayList<Order> getOrdersBySeller (String email) { return orderDAO.getOrdersBySeller(email); }

    public void deleteOrder(String id){orderDAO.deleteOrder(id); }

    public boolean updateOrder(Order r){return orderDAO.updateOrder(r);}
}
