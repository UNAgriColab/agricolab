package agricolab.service;

import agricolab.JsonModel.Update;
import agricolab.dao.OrderDAO;
import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;


@Service
public class OrderService {

    private OrderDAO orderDAO;
    private UserService userService;
    private OfferService offerService;

    @Autowired
    public OrderService(OrderDAO orderDAO, OfferService offerService , UserService userService ) {
        this.orderDAO = orderDAO;
        this.offerService = offerService;
        this.userService = userService;
    }

    public boolean addOrder(Order order) {
        // Check for orders on the same product

        // Now we populate the order fields with the product data
        Offer offerFromRef = offerService.getOffer(order.getOfferReference());
        order.setSellerEmail(Objects.requireNonNull(offerFromRef).getSellerEmail());
        order.setProductName(Objects.requireNonNull(offerFromRef).getProductName());
        order.setPresentation(Objects.requireNonNull(offerFromRef).getPresentation());
        order.setTotalPrice(Objects.requireNonNull(offerFromRef).getPricePresentation() * order.getNumberOfUnits());

        // Check for different buyer and seller (return false)
        if (order.getBuyerEmail().equalsIgnoreCase(order.getSellerEmail())) {
            System.out.println("You can't create an order request from yourself.");
            return false;
        }

        // Check for minimum order quantity
        if (order.getNumberOfUnits() < offerService.getOffer(order.getOfferReference()).getMinQuantity()) {
            System.out.println("You are attempting to buy less units than the minimum.");
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

    public ArrayList<Order> getActiveOrdersByBuyer(String email) { return orderDAO.getActiveOrdersByBuyer(email); }

    public ArrayList<Order> getOrdersBySeller(String email) {
        return orderDAO.getOrdersBySeller(email);
    }
    public ArrayList<Order> getActiveOrdersBySeller(String email){ return orderDAO.getActiveOrdersBySeller(email);}



    public void deleteOrder(String id) {
        orderDAO.deleteOrder(id);
    }

    //update methods
    public boolean updateOrderByBuyer(Update changes) {
        return orderDAO.updateOrderByBuyer(changes.getOrderId());
    }

    public boolean updateOrderBySeller(Update changes) {
        return orderDAO.updateOrderBySeller(changes);
    }

    public boolean updateOrderQualification(String orderId, int qualification){
        Order order = orderDAO.getOrder(orderId);
        User u = userService.getUser(order.getSellerEmail());
        double newQualification = ((u.getQualification() * u.getNumberOfReviews()) + qualification) /(u.getNumberOfReviews()+1);

        u.setQualification(newQualification);
        u.setNumberOfReviews(u.getNumberOfReviews()+1);
        userService.updateUser(u);
        order.setQualification(newQualification);
        return orderDAO.updateOrder(order);
    }
}
