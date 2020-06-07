package agricolab.service;

import agricolab.JsonModel.Update;
import agricolab.dao.OrderDAO;
import agricolab.model.Offer;
import agricolab.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;


@Service
public class OrderService {

    private OrderDAO orderDAO;
    private OfferService offerService;
    private static int CANCEL_STATE = 0;
    private static int FINAL_STATE = 5;
    private static int INIT_STATE = 1;

    @Autowired
    public OrderService(OrderDAO orderDAO, OfferService offerService) {
        this.orderDAO = orderDAO;
        this.offerService = offerService;
    }

    public boolean addOrder(Order order) {
        // Check for orders on the same product

        // Now we populate the order fields with the product data
        Offer offerFromRef = offerService.getOffer(order.getOfferReference());
        order.setSellerEmail(Objects.requireNonNull(offerFromRef).getSellerEmail());
        order.setProductName(Objects.requireNonNull(offerFromRef).getProductName());
        order.setPresentation(Objects.requireNonNull(offerFromRef).getPresentation());
        order.setTotalPrice(Objects.requireNonNull(offerFromRef).getPricePresentation() * order.getNumberOfUnits());
        order.setState(INIT_STATE);

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

    public ArrayList<Order> getActiveOrdersByBuyer(String email) {
        return orderDAO.getActiveOrdersByBuyer(email);
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

    public ArrayList<Order> getActiveOrdersBySeller(String email) {
        return orderDAO.getActiveOrdersBySeller(email);
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

    public boolean updateOrderStatus(String id, String email) {
        // Fetch order and status
        Order theOrder = orderDAO.getOrder(id);
        if(theOrder.equals(null)){
            return false;
        }
        int orderState = theOrder.getState();

        // Check if order is completed or cancelled (no possible update)
        if(orderState == CANCEL_STATE || orderState == FINAL_STATE){
            return false;
        }

        // Compare email to either buyer or seller
        if(email.equalsIgnoreCase(theOrder.getSellerEmail())){
            // UPDATE BY SELLER
            // Check if state is second to last
            if(orderState == FINAL_STATE - 1){
                return false;
            }
            // Update possible, delegate return to DAO
            return orderDAO.updateOrderStatus(id, orderState + 1);
        } else if(email.equalsIgnoreCase(theOrder.getBuyerEmail())){
            // UPDATE BY BUYER
            // Check if state is not second to last
            if(orderState != FINAL_STATE - 1){
                return false;
            }
            // Update possible, delegate return to DAO
            return orderDAO.updateOrderStatus(id, orderState + 1);
        } else {
            return false;
        }
    }

    public boolean cancelOrder(String id, String email) {
        // Fetch order and status
        Order theOrder = orderDAO.getOrder(id);
        if(theOrder.equals(null)){
            return false;
        }
        int orderState = theOrder.getState();

        // Check if order is completed or cancelled (no possible cancel)
        if(orderState == CANCEL_STATE || orderState == FINAL_STATE){
            return false;
        }

        // Compare email to either buyer or seller
        if(email.equalsIgnoreCase(theOrder.getSellerEmail())){
            // CANCEL BY SELLER
            // No restrictions, delegate return to DAO
            return orderDAO.updateOrderStatus(id, CANCEL_STATE);
        } else if(email.equalsIgnoreCase(theOrder.getBuyerEmail())){
            // CANCEL BY BUYER
            // Check if state is initial (unconfirmed)
            if(orderState != INIT_STATE){
                return false;
            }
            // Cancel possible, delegate return to DAO
            return orderDAO.updateOrderStatus(id, CANCEL_STATE);
        } else {
            return false;
        }
    }
}
