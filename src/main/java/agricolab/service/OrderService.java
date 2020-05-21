package agricolab.service;

import agricolab.JsonModel.Update;
import agricolab.dao.OrderDAO;
import agricolab.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public boolean addOrder(Order order) {
        // Check for different buyer and seller (return false)
        if (order.getBuyerEmail().equalsIgnoreCase(order.getSellerEmail())) {
            return false;
        }
        // Check for minimum order quantity
        if (order.getNumberOfUnits() < OfferService.getOffer(order.getOfferReference()).getMinQuantity()) {
            return false;
        }

        // No inconsistencies, delegate return to DAO status
        return orderDAO.createOrder(order);
    }

    public Order getOrder(String id) {
        return orderDAO.getOrder(id);
    }

    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public ArrayList<Order> getOrdersByBuyer(String email) {
        return orderDAO.getOrdersByBuyer(email);
    }

    public ArrayList<Order> getOrdersByOffer(String offerId) {
        return orderDAO.getOrdersByOffer(offerId);
    }

    public ArrayList<Order> getOrdersBySeller(String email) {
        return orderDAO.getOrdersBySeller(email);
    }

    public ArrayList<Order> getActiveOrders() {
        return orderDAO.getActiveOrders();
    }

    public ArrayList<Order> getOrdersByProduct(String productName) {
        return orderDAO.getOrdersByProduct(productName);
    }

    public void deleteOrder(String id) {
        orderDAO.deleteOrder(id);
    }

    public boolean updateOrderByBuyer(Update changes) {
        return orderDAO.updateOrderByBuyer(changes.getOrderId());
    }

    public boolean updateOrderBySeller(Update changes) {
        return orderDAO.updateOrderBySeller(changes);
    }

    public int getLastOrderId() {
        return orderDAO.getLastOrderId();
    }
}
