package agricolab.service;

import agricolab.dao.OrderDAO;
import agricolab.model.Comment;
import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;


@Service
public class OrderService {

    public static int STATE_INIT = 2;
    public static int STATE_CANCELED = 0;
    public static int STATE_COMPLETED = 1;
    public static int STATE_SHIPPED = 4;
    private final OrderDAO orderDAO;
    private final UserService userService;
    private final OfferService offerService;
    private final CommentService commentService;

    @Autowired
    public OrderService(@Qualifier("OrderFirestore") OrderDAO orderDAO, OfferService offerService, UserService userService, CommentService commentService) {
        this.orderDAO = orderDAO;
        this.offerService = offerService;
        this.userService = userService;
        this.commentService = commentService;
    }

    public boolean addOrder(Order order) {
        // Check for orders on the same product

        // Now we populate the order fields with the product data
        Offer offerFromRef = offerService.getOffer(order.getOfferReference());
        order.setSellerEmail(Objects.requireNonNull(offerFromRef).getSellerEmail());
        order.setProductName(Objects.requireNonNull(offerFromRef).getProductName());
        order.setPresentation(Objects.requireNonNull(offerFromRef).getPresentation());
        order.setTotalPrice(Objects.requireNonNull(offerFromRef).getPricePresentation() * order.getNumberOfUnits());
        order.setState(STATE_INIT);

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

        User seller = userService.getUser(order.getSellerEmail());
        User buyer = userService.getUser(order.getBuyerEmail());

        // TODO: 15/06/2020 Metodos que actualicen los respectivos campos de los usuarios arriba 
        seller.setNumberOfOrdersrecieved(seller.getNumberOfOrdersrecieved()+1);
        buyer.setNumberOfOrdersdone(buyer.getNumberOfOrdersdone()+1);
        // No inconsistencies, delegate return to DAO status
        return orderDAO.createOrder(order);
    }

    public Order getOrder(String id) {
        return orderDAO.getOrder(id);
    }

    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public ArrayList<Order> getOrdersByBuyer(String email, String productName, int state) {
        return orderDAO.getOrdersByBuyer(email, productName, state);
    }

    public ArrayList<Order> getActiveOrdersByBuyer(String email, String productName, int state) {
        return orderDAO.getActiveOrdersByBuyer(email, productName, state);
    }

    public ArrayList<Order> getOrdersBySeller(String email, String productName, int state) {
        return orderDAO.getOrdersBySeller(email, productName, state);
    }

    public ArrayList<Order> getActiveOrdersBySeller(String email, String productName, int state) {
        return orderDAO.getActiveOrdersBySeller(email, productName, state);
    }

    public boolean deleteOrder(String id) {
        return orderDAO.deleteOrder(id);
    }

    public int getLastOrderId() {
        return orderDAO.getLastOrderId();
    }

    public boolean updateOrderQualification(Comment comment) {
        if ((1 <= comment.getQualification() && comment.getQualification() <= 5) && (orderDAO.getOrder(comment.getOrderReference()).getState() == 1)) {
            Order order = orderDAO.getOrder(comment.getOrderReference());
            if (order.getQualification() != 0) {
                System.out.println("Ya se habia realizado un review de esta orden, no se puede hacer 2 veces");
                return false;
            }
            User u = userService.getUser(order.getSellerEmail());
            Offer o = offerService.getOffer(order.getOfferReference());
            commentService.addComment(comment);

            double newQualification = ((u.getQualification() * u.getNumberOfReviews()) + comment.getQualification()) / (u.getNumberOfReviews() + 1);

            u.setQualification(newQualification);
            u.setNumberOfReviews(u.getNumberOfReviews() + 1);
            userService.updateUserQualification(u.getEmail(), u.getQualification());

            int offerQualification = ((o.getQualification() * o.getNumberOfReviews()) + comment.getQualification()) / (o.getNumberOfReviews() + 1);
            o.setQualification(offerQualification);
            o.setNumberOfReviews(o.getNumberOfReviews() + 1);
            offerService.updateOfferReviews(String.valueOf(o.getId()),o.getQualification(),o.getNumberOfReviews());

            order.setQualification(comment.getQualification());
            return orderDAO.updateOrder(Integer.parseInt(order.getId()),order.getQualification());
        } else {
            System.out.println("calificacion fuera de rango no puede ser procesada");
            return false;
        }
    }

    public boolean updateOrderStatus(String id, String email) {
        // Fetch order and status
        Order theOrder = orderDAO.getOrder(id);
        if (theOrder.equals(null)) {
            return false;
        }
        int orderState = theOrder.getState();

        // Check if order is completed or cancelled (no possible update)
        if (orderState == STATE_CANCELED || orderState == STATE_COMPLETED) {
            return false;
        }

        // Compare email to either buyer or seller
        if (email.equalsIgnoreCase(theOrder.getSellerEmail())) {
            // UPDATE BY SELLER
            if (orderState == STATE_SHIPPED) {
                return false;
            }
            // Delegate return to DAO
            return orderDAO.updateOrderStatus(id, orderState + 1);
        } else if (email.equalsIgnoreCase(theOrder.getBuyerEmail())) {
            // UPDATE BY BUYER
            if (orderState != STATE_SHIPPED) {
                return false;
            }
            // Delegate return to DAO
            return orderDAO.updateOrderStatus(id, STATE_COMPLETED);
        } else {
            return false;
        }
    }

    public boolean cancelOrder(String id, String email) {
        // Fetch order and status
        Order theOrder = orderDAO.getOrder(id);
        if (theOrder.equals(null)) {
            return false;
        }
        int orderState = theOrder.getState();

        // Check if order is completed or cancelled (no possible cancel)
        if (orderState == STATE_CANCELED || orderState == STATE_COMPLETED) {
            return false;
        }

        // Compare email to either buyer or seller
        if (email.equalsIgnoreCase(theOrder.getSellerEmail())) {
            // CANCEL BY SELLER
            // No restrictions, delegate return to DAO
            return orderDAO.updateOrderStatus(id, STATE_CANCELED);
        } else if (email.equalsIgnoreCase(theOrder.getBuyerEmail())) {
            // CANCEL BY BUYER
            // Check if state is initial (unconfirmed)
            if (orderState != STATE_INIT) {
                return false;
            }
            // Cancel possible, delegate return to DAO
            return orderDAO.updateOrderStatus(id, STATE_CANCELED);
        } else {
            return false;
        }
    }

    public boolean updateOrder(int id, double qualification) {
        return orderDAO.updateOrder(id, qualification);
    }
}
